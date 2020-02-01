package com.yura.repairservice.repository;

import com.yura.repairservice.entity.ReviewEntity;

import java.util.List;

public interface ReviewRepository extends CrudRepository<ReviewEntity, Integer> {

    List<ReviewEntity> findAllByOrder(Integer orderId, Integer offset, Integer limit);

    List<ReviewEntity> findAllByClient(Integer clientId, Integer offset, Integer limit);

    Integer countByOrderId(Integer orderId);

    Integer countByClientId(Integer clientId);
}
