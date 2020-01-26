package com.yura.repairservice.service.impl;

import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.order.Status;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.entity.OrderEntity;
import com.yura.repairservice.exception.EntityNotFoundException;
import com.yura.repairservice.repository.OrderRepository;
import com.yura.repairservice.service.OrderService;
import com.yura.repairservice.service.mapper.EntityMapper;
import com.yura.repairservice.service.validator.Validator;

import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final EntityMapper<OrderEntity, Order> mapper;
    private final Validator<Order> validator;

    public OrderServiceImpl(OrderRepository repository, EntityMapper<OrderEntity, Order> mapper, Validator<Order> validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public void add(Order order) {
        validator.validate(order);
        repository.save(mapper.mapDomainToEntity(order));
    }

    @Override
    public Order findById(Integer id) {
        return repository
                .findById(id)
                .map(mapper::mapEntityToDomain)
                .orElseThrow(()->new EntityNotFoundException("Order not found with provided id " + id));
    }

    @Override
    public List<Order> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findByClient(Integer clientId) {
        return repository
                .findAllByClientId(clientId)
                .stream()
                .map(mapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findByMaster(Integer masterId) {
        return repository
                .findAllByMasterId(masterId)
                .stream()
                .map(mapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findByStatus(Status status) {
        return repository
                .findAllByStatus(status)
                .stream()
                .map(mapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void acceptOrder(Order order) {
        repository.update(mapper.mapDomainToEntity(new Order(order, Status.ACCEPTED)));
    }

    @Override
    public void rejectOrder(Order order, String rejectionReason) {
        repository.update(mapper.mapDomainToEntity(new Order(order, Status.REJECTED, rejectionReason)));
    }

    @Override
    public void processOrder(Order order, User master) {
        repository.update(mapper.mapDomainToEntity(new Order(order, Status.PROCESSING, master)));
    }

    @Override
    public void completeOrder(Order order) {
        repository.update(mapper.mapDomainToEntity(new Order(order, Status.COMPLETED)));
    }

    @Override
    public void setPrice(Order order, Double price) {
        repository.update(mapper.mapDomainToEntity(new Order(order, price)));
    }
}
