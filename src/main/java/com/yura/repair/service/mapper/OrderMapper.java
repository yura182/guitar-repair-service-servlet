package com.yura.repair.service.mapper;

import com.yura.repair.dto.InstrumentDto;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.dto.UserDto;
import com.yura.repair.entity.InstrumentEntity;
import com.yura.repair.entity.OrderEntity;
import com.yura.repair.entity.UserEntity;

import java.util.Objects;

public class OrderMapper implements EntityMapper<OrderEntity, OrderDto> {
    private final EntityMapper<UserEntity, UserDto> userMapper;
    private final EntityMapper<InstrumentEntity, InstrumentDto> instrumentMapper;

    public OrderMapper(EntityMapper<UserEntity, UserDto> userMapper, EntityMapper<InstrumentEntity, InstrumentDto> instrumentMapper) {
        this.userMapper = userMapper;
        this.instrumentMapper = instrumentMapper;
    }

    @Override
    public OrderDto mapEntityToDomain(OrderEntity orderEntity) {
        return Objects.isNull(orderEntity) ? null : OrderDto.builder()
                .withId(orderEntity.getId())
                .withUser(userMapper.mapEntityToDomain(orderEntity.getClient()))
                .withMaster(userMapper.mapEntityToDomain(orderEntity.getMaster()))
                .withInstrument(instrumentMapper.mapEntityToDomain(orderEntity.getInstrument()))
                .withDate(orderEntity.getDateTime())
                .withService(orderEntity.getService())
                .withPrice(orderEntity.getPrice())
                .withStatus(orderEntity.getStatus())
                .withRejectionReason(orderEntity.getRejectionReason())
                .build();
    }

    @Override
    public OrderEntity mapDomainToEntity(OrderDto orderDto) {
        return Objects.isNull(orderDto) ? null : OrderEntity.builder()
                .withId(orderDto.getId())
                .withUser(userMapper.mapDomainToEntity(orderDto.getClient()))
                .withMaster(userMapper.mapDomainToEntity(orderDto.getMaster()))
                .withInstrument(instrumentMapper.mapDomainToEntity(orderDto.getInstrument()))
                .withDate(orderDto.getDateTime())
                .withService(orderDto.getService())
                .withPrice(orderDto.getPrice())
                .withStatus(orderDto.getStatus())
                .withRejectionReason(orderDto.getRejectionReason())
                .build();
    }
}
