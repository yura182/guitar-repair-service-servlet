package com.yura.repairservice.service.impl;

import com.yura.repairservice.domain.order.Comment;
import com.yura.repairservice.entity.CommentEntity;
import com.yura.repairservice.exception.EntityNotFoundException;
import com.yura.repairservice.repository.CommentRepository;
import com.yura.repairservice.service.CommentService;
import com.yura.repairservice.service.mapper.EntityMapper;
import com.yura.repairservice.service.validator.Validator;

import java.util.List;
import java.util.stream.Collectors;

public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;
    private final EntityMapper<CommentEntity, Comment> mapper;
    private final Validator<Comment> validator;

    public CommentServiceImpl(CommentRepository repository, EntityMapper<CommentEntity, Comment> mapper, Validator<Comment> validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public void add(Comment comment) {
        validator.validate(comment);

        repository.save(mapper.mapDomainToEntity(comment));
    }

    @Override
    public Comment findById(Integer id) {
        return repository
                .findById(id)
                .map(mapper::mapEntityToDomain)
                .orElseThrow(()->new EntityNotFoundException("Comment not found with provided id " + id));
    }

    @Override
    public void delete(Integer commentId) {
        repository.deleteById(commentId);
    }

    @Override
    public List<Comment> findAll(Integer offset, Integer limit) {
        return repository
                .findAll(offset, limit)
                .stream()
                .map(mapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Comment> findAllByOrder(Integer orderId) {
        return repository
                .findAllByOrder(orderId)
                .stream()
                .map(mapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Comment> findAllByClient(Integer clientId) {
        return repository
                .findAllByClient(clientId)
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
