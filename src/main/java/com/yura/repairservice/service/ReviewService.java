package com.yura.repairservice.service;

import com.yura.repairservice.domain.order.Review;

import java.util.List;

public interface ReviewService {

    void add(Review review);

    void delete(Integer commentId);

    Review findById(Integer id);

    List<Review> findAll(Integer offset, Integer limit);

    List<Review> findAllByOrder(Integer orderId, Integer offset, Integer limit);

    List<Review> findAllByClient(Integer clientId, Integer offset, Integer limit);

    Integer numberOfEntries();

    Integer numberOfEntriesByOrderId(Integer orderId);

    Integer numberOfEntriesByClientId(Integer clientId);
}
