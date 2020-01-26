package com.yura.repairservice;

import com.yura.repairservice.context.ApplicationContextInjector;
import com.yura.repairservice.domain.instrument.Instrument;
import com.yura.repairservice.domain.order.Status;
import com.yura.repairservice.domain.user.Role;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.entity.InstrumentEntity;
import com.yura.repairservice.entity.OrderEntity;
import com.yura.repairservice.entity.UserEntity;
import com.yura.repairservice.repository.OrderRepository;
import com.yura.repairservice.service.InstrumentService;
import com.yura.repairservice.service.UserService;
import com.yura.repairservice.service.mapper.InstrumentMapper;
import com.yura.repairservice.service.mapper.UserMapper;

import java.time.LocalDateTime;

public class Test {
    public static void main(String[] args) {
        ApplicationContextInjector injector = ApplicationContextInjector.getInstance();
        InstrumentService instrumentService = injector.getInstrumentService();
        UserService userService = injector.getUserService();
        OrderRepository orderRepository = injector.getOrderRepository();
        InstrumentEntity instrument = InstrumentEntity.builder()
                .withId(10)
                .build();
        UserEntity user = UserEntity.builder()
                .withId(2)
                .build();

        UserMapper userMapper = new UserMapper();
        InstrumentMapper instrumentMapper = new InstrumentMapper();

        OrderEntity orderEntity = OrderEntity.builder()
                .withUser(user)
                .withDate(LocalDateTime.now())
                .withInstrument(instrument)
                .withService("Destroy")
                .withStatus(Status.NEW)
                .build();

        orderRepository.save(orderEntity);


    }
}
