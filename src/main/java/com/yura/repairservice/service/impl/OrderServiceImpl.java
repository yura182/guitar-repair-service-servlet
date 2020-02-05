package com.yura.repairservice.service.impl;

import com.yura.repairservice.domain.instrument.Instrument;
import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.order.Status;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.entity.OrderEntity;
import com.yura.repairservice.exception.OrderNotFoundException;
import com.yura.repairservice.repository.OrderRepository;
import com.yura.repairservice.service.OrderService;
import com.yura.repairservice.service.mapper.EntityMapper;
import com.yura.repairservice.service.validator.Validator;

import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final EntityMapper<OrderEntity, Order> orderMapper;
    private final Validator<Order> orderValidator;
    private final Validator<Instrument> instrumentValidator;

    public OrderServiceImpl(OrderRepository repository, EntityMapper<OrderEntity, Order> orderMapper,
                            Validator<Order> OrderValidator, Validator<Instrument> instrumentValidator) {
        this.repository = repository;
        this.orderMapper = orderMapper;
        this.orderValidator = OrderValidator;
        this.instrumentValidator = instrumentValidator;
    }

    @Override
    public void add(Order order) {
        orderValidator.validate(order);
        instrumentValidator.validate(order.getInstrument());
        repository.save(orderMapper.mapDomainToEntity(order));
    }

    @Override
    public Order findById(Integer id) {
        return repository
                .findById(id)
                .map(orderMapper::mapEntityToDomain)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with provided id " + id));
    }

    @Override
    public List<Order> findAll(Integer offset, Integer limit) {
        return repository
                .findAll(offset, limit)
                .stream()
                .map(orderMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findByClient(Integer clientId, Integer offset, Integer limit) {
        return repository
                .findAllByClientId(clientId, offset, limit)
                .stream()
                .map(orderMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findByMaster(Integer masterId, Integer offset, Integer limit) {
        return repository
                .findAllByMasterId(masterId, offset, limit)
                .stream()
                .map(orderMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findByStatus(Status status, Integer offset, Integer limit) {
        return repository
                .findAllByStatus(status, offset, limit)
                .stream()
                .map(orderMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void acceptOrder(Order order, Double price) {
        repository.update(orderMapper.mapDomainToEntity(new Order(order, Status.ACCEPTED, price)));
    }

    @Override
    public void rejectOrder(Order order, String rejectionReason) {
        repository.update(orderMapper.mapDomainToEntity(new Order(order, Status.REJECTED, rejectionReason)));
    }

    @Override
    public void processOrder(Order order, User master) {
        repository.update(orderMapper.mapDomainToEntity(new Order(order, Status.PROCESSING, master)));
    }

    @Override
    public void completeOrder(Order order) {
        repository.update(orderMapper.mapDomainToEntity(new Order(order, Status.COMPLETED)));
    }

    @Override
    public void setPrice(Order order, Double price) {
        repository.update(orderMapper.mapDomainToEntity(new Order(order, price)));
    }

    @Override
    public Integer numberOfEntries() {
        return repository.countAll();
    }

    @Override
    public Integer numberOfEntriesByClientId(Integer clientId) {
        return repository.countByClientId(clientId);
    }

    @Override
    public Integer numberOfEntriesByMasterId(Integer masterId) {
        return repository.countByMasterId(masterId);
    }

    @Override
    public Integer numberOfEntriesByStatus(Status status) {
        return repository.countByStatus(status);
    }
}
