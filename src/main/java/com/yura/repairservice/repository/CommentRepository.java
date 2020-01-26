package com.yura.repairservice.repository;

import com.yura.repairservice.entity.CommentEntity;

import java.util.List;

public interface CommentRepository extends CrudRepository<CommentEntity, Integer> {

    List<CommentEntity> findAllByOrder(Integer orderId);

    List<CommentEntity> findAllByClient(Integer clientId);

    Integer countByOrderId(Integer orderId);

    Integer countByClientId(Integer clientId);
}
