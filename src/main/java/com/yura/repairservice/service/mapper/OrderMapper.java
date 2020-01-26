package com.yura.repairservice.service.mapper;

import com.yura.repairservice.domain.instrument.Instrument;
import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.entity.InstrumentEntity;
import com.yura.repairservice.entity.OrderEntity;
import com.yura.repairservice.entity.UserEntity;

import java.util.Objects;

public class OrderMapper implements EntityMapper<OrderEntity, Order> {
    private final EntityMapper<UserEntity, User> userMapper;
    private final EntityMapper<InstrumentEntity, Instrument> instrumentMapper;

    public OrderMapper(EntityMapper<UserEntity, User> userMapper, EntityMapper<InstrumentEntity, Instrument> instrumentMapper) {
        this.userMapper = userMapper;
        this.instrumentMapper = instrumentMapper;
    }

    @Override
    public Order mapEntityToDomain(OrderEntity orderEntity) {
        return Objects.isNull(orderEntity) ? null : Order.builder()
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
    public OrderEntity mapDomainToEntity(Order order) {
        return Objects.isNull(order) ? null : OrderEntity.builder()
                .withId(order.getId())
                .withUser(userMapper.mapDomainToEntity(order.getClient()))
                .withMaster(userMapper.mapDomainToEntity(order.getMaster()))
                .withInstrument(instrumentMapper.mapDomainToEntity(order.getInstrument()))
                .withDate(order.getDateTime())
                .withService(order.getService())
                .withPrice(order.getPrice())
                .withStatus(order.getStatus())
                .withRejectionReason(order.getRejectionReason())
                .build();
    }
}
