package com.yura.repair.service.mapper;

import com.yura.repair.dto.OrderDto;
import com.yura.repair.dto.ReviewDto;
import com.yura.repair.dto.UserDto;
import com.yura.repair.entity.OrderEntity;
import com.yura.repair.entity.ReviewEntity;
import com.yura.repair.entity.UserEntity;

import java.util.Objects;

public class ReviewMapper implements EntityMapper<ReviewEntity, ReviewDto> {
    private final EntityMapper<UserEntity, UserDto> userMapper;
    private final EntityMapper<OrderEntity, OrderDto> orderMapper;

    public ReviewMapper(EntityMapper<UserEntity, UserDto> userMapper, EntityMapper<OrderEntity, OrderDto> orderMapper) {
        this.userMapper = userMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public ReviewEntity mapDtoToEntity(ReviewDto entity) {
        return Objects.isNull(entity) ? null : ReviewEntity.builder()
                .withId(entity.getId())
                .withClient(userMapper.mapDtoToEntity(entity.getClient()))
                .withOrder(orderMapper.mapDtoToEntity(entity.getOrder()))
                .withText(entity.getText())
                .withDate(entity.getDate())
                .build();
    }

    @Override
    public ReviewDto mapEntityToDto(ReviewEntity domain) {
        return Objects.isNull(domain) ? null : ReviewDto.builder()
                .withId(domain.getId())
                .withClient(userMapper.mapEntityToDto(domain.getClient()))
                .withOrder(orderMapper.mapEntityToDto(domain.getOrder()))
                .withText(domain.getText())
                .withDate(domain.getDate())
                .build();
    }
}
