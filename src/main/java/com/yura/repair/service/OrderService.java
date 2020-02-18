package com.yura.repair.service;

import com.yura.repair.dto.OrderDto;
import com.yura.repair.dto.UserDto;
import com.yura.repair.entity.Status;

import java.util.List;

public interface OrderService {

    void add(OrderDto orderDto);

    OrderDto findById(Integer id);

    List<OrderDto> findAll(Integer offset, Integer limit);

    List<OrderDto> findByClient(Integer clientId, Integer offset, Integer limit);

    List<OrderDto> findByMaster(Integer masterId, Integer offset, Integer limit);

    List<OrderDto> findByStatus(Status status, Integer offset, Integer limit);

    void acceptOrder(OrderDto orderDto, Double price);

    void rejectOrder(OrderDto orderDto, String rejectionReason);

    boolean processOrder(OrderDto orderDto, UserDto master);

    void completeOrder(OrderDto orderDto);

    void setPrice(OrderDto orderDto, Double price);

    Integer numberOfEntries();

    Integer numberOfEntriesByClientId(Integer clientId);

    Integer numberOfEntriesByMasterId(Integer masterId);

    Integer numberOfEntriesByStatus(Status status);
}
