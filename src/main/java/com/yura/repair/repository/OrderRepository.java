package com.yura.repair.repository;

import com.yura.repair.entity.OrderEntity;
import com.yura.repair.entity.Status;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {

    List<OrderEntity> findAllByClientId(Integer clientId, Integer offset, Integer limit);

    List<OrderEntity> findAllByMasterId(Integer masterId, Integer offset, Integer limit);

    List<OrderEntity> findAllByStatus(Status status, Integer offset, Integer limit);

    Integer countByClientId(Integer clientId);

    Integer countByMasterId(Integer masterId);

    Integer countByStatus(Status status);
}
