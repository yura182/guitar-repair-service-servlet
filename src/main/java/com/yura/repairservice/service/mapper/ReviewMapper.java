package com.yura.repairservice.service.mapper;

import com.yura.repairservice.domain.order.Review;
import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.entity.ReviewEntity;
import com.yura.repairservice.entity.OrderEntity;
import com.yura.repairservice.entity.UserEntity;

import java.util.Objects;

public class ReviewMapper implements EntityMapper<ReviewEntity, Review> {
    private final EntityMapper<UserEntity, User> userMapper;
    private final EntityMapper<OrderEntity, Order> orderMapper;

    public ReviewMapper(EntityMapper<UserEntity, User> userMapper, EntityMapper<OrderEntity, Order> orderMapper) {
        this.userMapper = userMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public ReviewEntity mapDomainToEntity(Review entity) {
        return Objects.isNull(entity) ? null : ReviewEntity.builder()
                .withId(entity.getId())
                .withClient(userMapper.mapDomainToEntity(entity.getClient()))
                .withOrder(orderMapper.mapDomainToEntity(entity.getOrder()))
                .withText(entity.getText())
                .withDate(entity.getDate())
                .build();
    }

    @Override
    public Review mapEntityToDomain(ReviewEntity domain) {
        return Objects.isNull(domain) ? null : Review.builder()
                .withId(domain.getId())
                .withClient(userMapper.mapEntityToDomain(domain.getClient()))
                .withOrder(orderMapper.mapEntityToDomain(domain.getOrder()))
                .withText(domain.getText())
                .withDate(domain.getDate())
                .build();
    }
}
