package com.yura.repairservice.service;

import com.yura.repairservice.domain.order.Comment;

import java.util.List;

public interface CommentService {

    void add(Comment comment);

    void delete(Integer commentId);

    Comment findById(Integer id);

    List<Comment> findAll();

    List<Comment> findAllByOrder(Integer orderId);

    List<Comment> findAllByClient(Integer clientId);
}
