package com.yura.repairservice.service.mapper;

import com.yura.repairservice.domain.instrument.Instrument;
import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.order.Status;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.entity.InstrumentEntity;
import com.yura.repairservice.entity.OrderEntity;
import com.yura.repairservice.entity.UserEntity;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderMapperTest {
    private static final User USER = User.builder().build();
    private static final UserEntity USER_ENTITY = UserEntity.builder().build();
    private static final Instrument INSTRUMENT = Instrument.builder().build();
    private static final InstrumentEntity INSTRUMENT_ENTITY = InstrumentEntity.builder().build();
    private static final Order ORDER = getOrder();
    private static final OrderEntity ORDER_ENTITY = getOrderEntity();

    private UserMapper userMapper;
    private InstrumentMapper instrumentMapper;
    private OrderMapper orderMapper;

    @Before
    public void init() {
        userMapper = mock(UserMapper.class);
        instrumentMapper = mock(InstrumentMapper.class);

        orderMapper = new OrderMapper(userMapper, instrumentMapper);
    }

    @Test
    public void mapEntityToDomainShouldMapToDomain() {
        when(userMapper.mapEntityToDomain(USER_ENTITY)).thenReturn(USER);
        when(instrumentMapper.mapEntityToDomain(INSTRUMENT_ENTITY)).thenReturn(INSTRUMENT);

        Order actual = orderMapper.mapEntityToDomain(ORDER_ENTITY);

        assertEquals(ORDER, actual);
    }

    @Test
    public void mapDomainToEntityShouldReturnEntity() {
        when(userMapper.mapDomainToEntity(USER)).thenReturn(USER_ENTITY);
        when(instrumentMapper.mapDomainToEntity(INSTRUMENT)).thenReturn(INSTRUMENT_ENTITY);

        OrderEntity actual = orderMapper.mapDomainToEntity(ORDER);

        assertEquals(ORDER_ENTITY, actual);
    }

    private static Order getOrder() {
        return Order.builder()
                .withId(1)
                .withUser(USER)
                .withMaster(USER)
                .withInstrument(INSTRUMENT)
                .withDate(LocalDateTime.of(1990, 12, 12, 12, 12))
                .withService("Service")
                .withPrice(1.1)
                .withStatus(Status.NEW)
                .build();
    }

    private static OrderEntity getOrderEntity() {
        return OrderEntity.builder()
                .withId(1)
                .withUser(USER_ENTITY)
                .withMaster(USER_ENTITY)
                .withInstrument(INSTRUMENT_ENTITY)
                .withDate(LocalDateTime.of(1990, 12, 12, 12, 12))
                .withService("Service")
                .withPrice(1.1)
                .withStatus(Status.NEW)
                .build();
    }
}