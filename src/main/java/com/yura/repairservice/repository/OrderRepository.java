package com.yura.repairservice.repository;

import com.yura.repairservice.domain.order.Status;
import com.yura.repairservice.entity.OrderEntity;
import com.yura.repairservice.entity.UserEntity;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {

    List<OrderEntity> findAllByClientId(Integer clientId);

    List<OrderEntity> findAllByMasterId(Integer masterId);

    List<OrderEntity> findAllByStatus(Status status);
}
