package com.yura.repair.repository;

import com.yura.repair.entity.ReviewEntity;

import java.util.List;

public interface ReviewRepository extends CrudRepository<ReviewEntity, Integer> {

    List<ReviewEntity> findAllByOrder(Integer orderId, Integer offset, Integer limit);

    List<ReviewEntity> findAllByClient(Integer clientId, Integer offset, Integer limit);

    Integer countByOrderId(Integer orderId);

    Integer countByClientId(Integer clientId);
}
