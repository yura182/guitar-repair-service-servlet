package com.yura.repairservice.repository;

import com.yura.repairservice.domain.order.Status;
import com.yura.repairservice.entity.OrderEntity;
import com.yura.repairservice.entity.UserEntity;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {

    List<OrderEntity> findAllByClientId(Integer clientId, Integer offset, Integer limit);

    List<OrderEntity> findAllByMasterId(Integer masterId, Integer offset, Integer limit);

    List<OrderEntity> findAllByStatus(Status status, Integer offset, Integer limit);

    Integer countByClientId(Integer clientId);

    Integer countByMasterId(Integer masterId);

    Integer countByStatus(Status status);
}
