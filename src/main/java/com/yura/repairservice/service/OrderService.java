package com.yura.repairservice.service;

import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.order.Status;
import com.yura.repairservice.domain.user.User;

import java.util.List;

public interface OrderService {

    void add(Order order);

    Order findById(Integer id);

    List<Order> findAll();

    List<Order> findByClient(Integer clientId);

    List<Order> findByMaster(Integer masterId);

    List<Order> findByStatus(Status status);

    void acceptOrder(Order order);

    void rejectOrder(Order order, String rejectionReason);

    void processOrder(Order order, User master);

    void completeOrder(Order order);

    void setPrice(Order order, Double price);
}
