package com.yura.repair.service;

import com.yura.repair.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    void add(ReviewDto reviewDto);

    void delete(Integer commentId);

    ReviewDto findById(Integer id);

    List<ReviewDto> findAll(Integer offset, Integer limit);

    List<ReviewDto> findAllByOrder(Integer orderId, Integer offset, Integer limit);

    List<ReviewDto> findAllByClient(Integer clientId, Integer offset, Integer limit);

    Integer numberOfEntries();

    Integer numberOfEntriesByOrderId(Integer orderId);

    Integer numberOfEntriesByClientId(Integer clientId);
}
