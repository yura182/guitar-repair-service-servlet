package com.yura.repairservice.service.impl;

import com.yura.repairservice.domain.instrument.Instrument;
import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.order.Status;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.entity.InstrumentEntity;
import com.yura.repairservice.entity.OrderEntity;
import com.yura.repairservice.entity.UserEntity;
import com.yura.repairservice.exception.InvalidParameterException;
import com.yura.repairservice.exception.OrderNotFoundException;
import com.yura.repairservice.repository.OrderRepository;
import com.yura.repairservice.service.mapper.EntityMapper;
import com.yura.repairservice.service.validator.Validator;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {
    private static final Order ORDER = getOrder();
    private static final OrderEntity ORDER_ENTITY = getOrderEntity();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private EntityMapper<OrderEntity, Order> orderMapper;

    @Mock
    private Validator<Order> orderValidator;

    @InjectMocks
    private OrderServiceImpl orderService;

    @After
    public void resetMocks() {
        reset(orderRepository, orderMapper, orderValidator);
    }

    @Test
    public void addShouldAddOrder() {
        when(orderMapper.mapDomainToEntity(ORDER)).thenReturn(ORDER_ENTITY);
        when(orderRepository.save(ORDER_ENTITY)).thenReturn(true);

        orderService.add(ORDER);

        verify(orderRepository).save(ORDER_ENTITY);
    }

    @Test
    public void addShouldThrowInvalidParameterExceptionForNull() {
        exception.expect(InvalidParameterException.class);
        doThrow(InvalidParameterException.class).when(orderValidator).validate(null);

        orderService.add(null);
    }

    @Test
    public void findByIdShouldReturnOrder() {
        when(orderRepository.findById(1)).thenReturn(Optional.of(ORDER_ENTITY));
        when(orderMapper.mapEntityToDomain(ORDER_ENTITY)).thenReturn(ORDER);

        Order actual = orderService.findById(1);

        assertEquals(ORDER, actual);
    }

    @Test
    public void findByIdShouldThrowUOrderNotFoundException() {
        exception.expect(OrderNotFoundException.class);
        exception.expectMessage("Order not found with provided id");
        when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());

        orderService.findById(1);
    }

    @Test
    public void findAllShouldReturnListOfOrders() {
        List<Order> expected = Collections.singletonList(ORDER);
        List<OrderEntity> orderEntities = Collections.singletonList(ORDER_ENTITY);

        when(orderRepository.findAll(anyInt(), anyInt())).thenReturn(orderEntities);
        when(orderMapper.mapEntityToDomain(ORDER_ENTITY)).thenReturn(ORDER);

        List<Order> actual = orderService.findAll(1, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void findAllShouldReturnEmptyList() {
        List<Order> expected = Collections.emptyList();
        List<OrderEntity> orderEntities = Collections.emptyList();

        when(orderRepository.findAll(anyInt(), anyInt())).thenReturn(orderEntities);

        List<Order> actual = orderService.findAll(1, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void findByClientShouldReturnListOfOrders() {
        List<Order> expected = Collections.singletonList(ORDER);
        List<OrderEntity> orderEntities = Collections.singletonList(ORDER_ENTITY);

        when(orderRepository.findAllByClientId(anyInt(), anyInt(), anyInt())).thenReturn(orderEntities);
        when(orderMapper.mapEntityToDomain(ORDER_ENTITY)).thenReturn(ORDER);

        List<Order> actual = orderService.findByClient(1, 1, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void findByClientShouldReturnEmptyList() {
        List<Order> expected = Collections.emptyList();
        List<OrderEntity> orderEntities = Collections.emptyList();

        when(orderRepository.findAllByClientId(anyInt(), anyInt(), anyInt())).thenReturn(orderEntities);

        List<Order> actual = orderService.findByClient(1, 1, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void findByMasterShouldReturnListOfOrders() {
        List<Order> expected = Collections.singletonList(ORDER);
        List<OrderEntity> orderEntities = Collections.singletonList(ORDER_ENTITY);

        when(orderRepository.findAllByMasterId(anyInt(), anyInt(), anyInt())).thenReturn(orderEntities);
        when(orderMapper.mapEntityToDomain(ORDER_ENTITY)).thenReturn(ORDER);

        List<Order> actual = orderService.findByMaster(1, 1, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void findByMasterShouldReturnEmptyList() {
        List<Order> expected = Collections.emptyList();
        List<OrderEntity> orderEntities = Collections.emptyList();

        when(orderRepository.findAllByMasterId(anyInt(), anyInt(), anyInt())).thenReturn(orderEntities);

        List<Order> actual = orderService.findByMaster(1, 1, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void findByStatusShouldReturnListOfOrders() {
        List<Order> expected = Collections.singletonList(ORDER);
        List<OrderEntity> orderEntities = Collections.singletonList(ORDER_ENTITY);

        when(orderRepository.findAllByStatus(any(Status.class), anyInt(), anyInt())).thenReturn(orderEntities);
        when(orderMapper.mapEntityToDomain(ORDER_ENTITY)).thenReturn(ORDER);

        List<Order> actual = orderService.findByStatus(Status.ACCEPTED, 1, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void findByStatusShouldReturnEmptyList() {
        List<Order> expected = Collections.emptyList();
        List<OrderEntity> orderEntities = Collections.emptyList();

        when(orderRepository.findAllByStatus(any(Status.class), anyInt(), anyInt())).thenReturn(orderEntities);

        List<Order> actual = orderService.findByStatus(Status.ACCEPTED, 1, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void acceptOrderShouldUpdateOrder() {
        when(orderMapper.mapDomainToEntity(any(Order.class))).thenReturn(ORDER_ENTITY);
        orderService.acceptOrder(ORDER, 1.1);
        verify(orderRepository).update(ORDER_ENTITY);
    }

    @Test
    public void rejectOrderShouldUpdateOrder() {
        when(orderMapper.mapDomainToEntity(any(Order.class))).thenReturn(ORDER_ENTITY);
        orderService.rejectOrder(ORDER, "reason");
        verify(orderRepository).update(ORDER_ENTITY);
    }

    @Test
    public void processOrderShouldUpdateOrder() {
        when(orderMapper.mapDomainToEntity(any(Order.class))).thenReturn(ORDER_ENTITY);
        orderService.processOrder(ORDER, User.builder().build());
        verify(orderRepository).update(ORDER_ENTITY);
    }

    @Test
    public void completeOrderShouldUpdateOrder() {
        when(orderMapper.mapDomainToEntity(any(Order.class))).thenReturn(ORDER_ENTITY);
        orderService.completeOrder(ORDER);
        verify(orderRepository).update(ORDER_ENTITY);
    }

    @Test
    public void setPriceShouldUpdateOrder() {
        when(orderMapper.mapDomainToEntity(any(Order.class))).thenReturn(ORDER_ENTITY);
        orderService.setPrice(ORDER, 1.1);
        verify(orderRepository).update(ORDER_ENTITY);
    }

    @Test
    public void numberOfEntriesShouldReturnNumberOfEntries() {
        when(orderRepository.countAll()).thenReturn(10);

        int expected = 10;
        int actual = orderService.numberOfEntries();

        assertEquals(expected, actual);
    }

    @Test
    public void numberOfEntriesByClientIdShouldReturnNumberOfEntries() {
        when(orderRepository.countByClientId(anyInt())).thenReturn(10);

        int expected = 10;
        int actual = orderService.numberOfEntriesByClientId(1);

        assertEquals(expected, actual);
    }

    @Test
    public void numberOfEntriesByMasterIdShouldReturnNumberOfEntries() {
        when(orderRepository.countByMasterId(anyInt())).thenReturn(10);

        int expected = 10;
        int actual = orderService.numberOfEntriesByMasterId(1);

        assertEquals(expected, actual);
    }

    @Test
    public void numberOfEntriesByStatusShouldReturnNumberOfEntries() {
        when(orderRepository.countByStatus(any(Status.class))).thenReturn(10);

        int expected = 10;
        int actual = orderService.numberOfEntriesByStatus(Status.ACCEPTED);

        assertEquals(expected, actual);
    }




    private static Order getOrder() {
        return Order.builder()
                .withInstrument(Instrument.builder()
                        .withBrand("Cort")
                        .withModel("123")
                        .withYear(1995)
                        .build())
                .withStatus(Status.NEW)
                .withDate(LocalDateTime.of(1990, 12, 12, 12, 12))
                .withUser(User.builder()
                        .withName("Yura")
                        .build())
                .withService("Service")
                .build();
    }

    private static OrderEntity getOrderEntity() {
        return OrderEntity.builder()
                .withId(1)
                .withInstrument(InstrumentEntity.builder()
                        .withId(1)
                        .withBrand("Cort")
                        .withModel("123")
                        .withYear(1995)
                        .build())
                .withStatus(Status.NEW)
                .withDate(LocalDateTime.of(1990, 12, 12, 12, 12))
                .withUser(UserEntity.builder()
                        .withId(1)
                        .withName("Yura")
                        .build())
                .withService("Service")
                .build();
    }
}