package com.yura.repairservice;

import com.yura.repairservice.context.ApplicationContextInjector;
import com.yura.repairservice.domain.order.Review;
import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.service.ReviewService;
import com.yura.repairservice.service.InstrumentService;
import com.yura.repairservice.service.OrderService;
import com.yura.repairservice.service.UserService;

import java.time.LocalDateTime;

public class Test {
    public static void main(String[] args) {
        ApplicationContextInjector injector = ApplicationContextInjector.getInstance();
        OrderService orderService = injector.getOrderService();
        UserService userService = injector.getUserService();
        InstrumentService instrumentService = injector.getInstrumentService();
        ReviewService reviewService = injector.getCommentService();

        User user = userService.findById(2);
        Order order = orderService.findById(2);

        Review review = Review.builder()
                .withClient(user)
                .withOrder(order)
                .withText("Good")
                .withDate(LocalDateTime.now())
                .build();

        userService.findAll(5, 2).forEach(System.out::println);
    }
}
