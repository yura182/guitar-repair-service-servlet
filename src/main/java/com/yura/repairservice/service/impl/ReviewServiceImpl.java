package com.yura.repairservice.service.impl;

import com.yura.repairservice.domain.order.Review;
import com.yura.repairservice.entity.ReviewEntity;
import com.yura.repairservice.exception.EntityNotFoundException;
import com.yura.repairservice.exception.UserNotFoundException;
import com.yura.repairservice.repository.ReviewRepository;
import com.yura.repairservice.service.ReviewService;
import com.yura.repairservice.service.mapper.EntityMapper;
import com.yura.repairservice.service.validator.Validator;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository repository;
    private final EntityMapper<ReviewEntity, Review> mapper;
    private final Validator<Review> validator;

    public ReviewServiceImpl(ReviewRepository repository, EntityMapper<ReviewEntity, Review> mapper, Validator<Review> validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public void add(Review review) {
        validator.validate(review);

        repository.save(mapper.mapDomainToEntity(review));
    }

    @Override
    public Review findById(Integer id) {
        return repository
                .findById(id)
                .map(mapper::mapEntityToDomain)
                .orElseThrow(() -> new EntityNotFoundException("Review not found with provided id " + id));
    }

    @Override
    public void delete(Integer commentId) {
        repository.deleteById(commentId);
    }

    @Override
    public List<Review> findAll(Integer offset, Integer limit) {
        return repository
                .findAll(offset, limit)
                .stream()
                .map(mapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Review> findAllByOrder(Integer orderId, Integer offset, Integer limit) {
        return repository
                .findAllByOrder(orderId, offset, limit)
                .stream()
                .map(mapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Review> findAllByClient(Integer clientId, Integer offset, Integer limit) {
        return repository
                .findAllByClient(clientId, offset, limit)
                .stream()
                .map(mapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Integer numberOfEntries() {
        return repository.countAll();
    }

    @Override
    public Integer numberOfEntriesByOrderId(Integer orderId) {
        return repository.countByOrderId(orderId);
    }

    @Override
    public Integer numberOfEntriesByClientId(Integer clientId) {
        return repository.countByClientId(clientId);
    }
}
