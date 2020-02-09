package com.yura.repair.service.impl;

import com.yura.repair.dto.InstrumentDto;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.entity.Status;
import com.yura.repair.dto.UserDto;
import com.yura.repair.entity.OrderEntity;
import com.yura.repair.exception.OrderNotFoundException;
import com.yura.repair.repository.OrderRepository;
import com.yura.repair.service.OrderService;
import com.yura.repair.service.mapper.EntityMapper;
import com.yura.repair.service.validator.Validator;

import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final EntityMapper<OrderEntity, OrderDto> orderMapper;
    private final Validator<OrderDto> orderValidator;
    private final Validator<InstrumentDto> instrumentValidator;

    public OrderServiceImpl(OrderRepository repository, EntityMapper<OrderEntity, OrderDto> orderMapper,
                            Validator<OrderDto> OrderValidator, Validator<InstrumentDto> instrumentValidator) {
        this.repository = repository;
        this.orderMapper = orderMapper;
        this.orderValidator = OrderValidator;
        this.instrumentValidator = instrumentValidator;
    }

    @Override
    public void add(OrderDto orderDto) {
        orderValidator.validate(orderDto);
        instrumentValidator.validate(orderDto.getInstrument());
        repository.save(orderMapper.mapDomainToEntity(orderDto));
    }

    @Override
    public OrderDto findById(Integer id) {
        return repository
                .findById(id)
                .map(orderMapper::mapEntityToDomain)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with provided id " + id));
    }

    @Override
    public List<OrderDto> findAll(Integer offset, Integer limit) {
        return repository
                .findAll(offset, limit)
                .stream()
                .map(orderMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findByClient(Integer clientId, Integer offset, Integer limit) {
        return repository
                .findAllByClientId(clientId, offset, limit)
                .stream()
                .map(orderMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findByMaster(Integer masterId, Integer offset, Integer limit) {
        return repository
                .findAllByMasterId(masterId, offset, limit)
                .stream()
                .map(orderMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findByStatus(Status status, Integer offset, Integer limit) {
        return repository
                .findAllByStatus(status, offset, limit)
                .stream()
                .map(orderMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void acceptOrder(OrderDto orderDto, Double price) {
        repository.update(orderMapper.mapDomainToEntity(new OrderDto(orderDto, Status.ACCEPTED, price)));
    }

    @Override
    public void rejectOrder(OrderDto orderDto, String rejectionReason) {
        repository.update(orderMapper.mapDomainToEntity(new OrderDto(orderDto, Status.REJECTED, rejectionReason)));
    }

    @Override
    public boolean processOrder(OrderDto orderDto, UserDto master) {
        return repository.update(orderMapper.mapDomainToEntity(new OrderDto(orderDto, Status.PROCESSING, master)));
    }

    @Override
    public void completeOrder(OrderDto orderDto) {
        repository.update(orderMapper.mapDomainToEntity(new OrderDto(orderDto, Status.COMPLETED)));
    }

    @Override
    public void setPrice(OrderDto orderDto, Double price) {
        repository.update(orderMapper.mapDomainToEntity(new OrderDto(orderDto, price)));
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
