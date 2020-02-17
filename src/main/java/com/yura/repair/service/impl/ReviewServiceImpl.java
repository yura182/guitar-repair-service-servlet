package com.yura.repair.service.impl;

import com.yura.repair.dto.ReviewDto;
import com.yura.repair.entity.ReviewEntity;
import com.yura.repair.exception.EntityNotFoundException;
import com.yura.repair.repository.ReviewRepository;
import com.yura.repair.service.ReviewService;
import com.yura.repair.service.mapper.EntityMapper;
import com.yura.repair.service.validator.Validator;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository repository;
    private final EntityMapper<ReviewEntity, ReviewDto> mapper;
    private final Validator<ReviewDto> validator;

    public ReviewServiceImpl(ReviewRepository repository, EntityMapper<ReviewEntity, ReviewDto> mapper, Validator<ReviewDto> validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public void add(ReviewDto reviewDto) {
        validator.validate(reviewDto);

        repository.save(mapper.mapDtoToEntity(reviewDto));
    }

    @Override
    public ReviewDto findById(Integer id) {
        return repository
                .findById(id)
                .map(mapper::mapEntityToDto)
                .orElseThrow(() -> new EntityNotFoundException("Review not found with provided id " + id));
    }

    @Override
    public void delete(Integer commentId) {
        repository.deleteById(commentId);
    }

    @Override
    public List<ReviewDto> findAll(Integer offset, Integer limit) {
        return repository
                .findAll(offset, limit)
                .stream()
                .map(mapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> findAllByOrder(Integer orderId, Integer offset, Integer limit) {
        return repository
                .findAllByOrder(orderId, offset, limit)
                .stream()
                .map(mapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> findAllByClient(Integer clientId, Integer offset, Integer limit) {
        return repository
                .findAllByClient(clientId, offset, limit)
                .stream()
                .map(mapper::mapEntityToDto)
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
