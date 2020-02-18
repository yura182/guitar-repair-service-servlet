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
    public OrderDto mapEntityToDto(OrderEntity orderEntity) {
        return Objects.isNull(orderEntity) ? null : OrderDto.builder()
                .withId(orderEntity.getId())
                .withUser(userMapper.mapEntityToDto(orderEntity.getClient()))
                .withMaster(userMapper.mapEntityToDto(orderEntity.getMaster()))
                .withInstrument(instrumentMapper.mapEntityToDto(orderEntity.getInstrument()))
                .withDate(orderEntity.getDateTime())
                .withService(orderEntity.getService())
                .withPrice(orderEntity.getPrice())
                .withStatus(orderEntity.getStatus())
                .withRejectionReason(orderEntity.getRejectionReason())
                .build();
    }

    @Override
    public OrderEntity mapDtoToEntity(OrderDto orderDto) {
        return Objects.isNull(orderDto) ? null : OrderEntity.builder()
                .withId(orderDto.getId())
                .withUser(userMapper.mapDtoToEntity(orderDto.getClient()))
                .withMaster(userMapper.mapDtoToEntity(orderDto.getMaster()))
                .withInstrument(instrumentMapper.mapDtoToEntity(orderDto.getInstrument()))
                .withDate(orderDto.getDateTime())
                .withService(orderDto.getService())
                .withPrice(orderDto.getPrice())
                .withStatus(orderDto.getStatus())
                .withRejectionReason(orderDto.getRejectionReason())
                .build();
    }
}
