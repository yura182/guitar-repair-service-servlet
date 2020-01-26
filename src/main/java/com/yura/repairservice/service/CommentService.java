package com.yura.repairservice.service;

import com.yura.repairservice.domain.order.Comment;

import java.util.List;

public interface CommentService {

    void add(Comment comment);

    void delete(Integer commentId);

    Comment findById(Integer id);

    List<Comment> findAll(Integer offset, Integer limit);

    List<Comment> findAllByOrder(Integer orderId);

    List<Comment> findAllByClient(Integer clientId);

    Integer numberOfEntries();

    Integer numberOfEntriesByOrderId(Integer orderId);

    Integer numberOfEntriesByClientId(Integer clientId);
}
