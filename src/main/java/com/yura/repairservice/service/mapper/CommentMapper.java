package com.yura.repairservice.service.mapper;

import com.yura.repairservice.domain.order.Comment;
import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.entity.CommentEntity;
import com.yura.repairservice.entity.OrderEntity;
import com.yura.repairservice.entity.UserEntity;

import java.util.Objects;

public class CommentMapper implements EntityMapper<CommentEntity, Comment> {
    private final EntityMapper<UserEntity, User> userMapper;
    private final EntityMapper<OrderEntity, Order> orderMapper;

    public CommentMapper(EntityMapper<UserEntity, User> userMapper, EntityMapper<OrderEntity, Order> orderMapper) {
        this.userMapper = userMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public CommentEntity mapDomainToEntity(Comment entity) {
        return Objects.isNull(entity) ? null : CommentEntity.builder()
                .withId(entity.getId())
                .withClient(userMapper.mapDomainToEntity(entity.getClient()))
                .withOrder(orderMapper.mapDomainToEntity(entity.getOrder()))
                .withText(entity.getText())
                .withDate(entity.getDate())
                .build();
    }

    @Override
    public Comment mapEntityToDomain(CommentEntity domain) {
        return Objects.isNull(domain) ? null : Comment.builder()
                .withId(domain.getId())
                .withClient(userMapper.mapEntityToDomain(domain.getClient()))
                .withOrder(orderMapper.mapEntityToDomain(domain.getOrder()))
                .withText(domain.getText())
                .withDate(domain.getDate())
                .build();
    }
}
