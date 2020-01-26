package com.yura.repairservice;

import com.yura.repairservice.context.ApplicationContextInjector;
import com.yura.repairservice.domain.instrument.Instrument;
import com.yura.repairservice.domain.order.Comment;
import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.order.Status;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.service.CommentService;
import com.yura.repairservice.service.InstrumentService;
import com.yura.repairservice.service.OrderService;
import com.yura.repairservice.service.UserService;
import org.apache.logging.log4j.core.util.JsonUtils;

import java.time.LocalDateTime;

public class Test {
    public static void main(String[] args) {
        ApplicationContextInjector injector = ApplicationContextInjector.getInstance();
        OrderService orderService = injector.getOrderService();
        UserService userService = injector.getUserService();
        InstrumentService instrumentService = injector.getInstrumentService();
        CommentService commentService = injector.getCommentService();

        User user = userService.findById(2);
        Order order = orderService.findById(2);

        Comment comment = Comment.builder()
                .withClient(user)
                .withOrder(order)
                .withText("Good")
                .withDate(LocalDateTime.now())
                .build();

//        commentService.add(comment);
        commentService.findAllByOrder(2).forEach(System.out::println);
    }
}
