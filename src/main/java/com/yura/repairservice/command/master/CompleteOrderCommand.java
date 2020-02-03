package com.yura.repairservice.command.master;

import com.yura.repairservice.command.Command;
import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class CompleteOrderCommand implements Command {

    private final OrderService orderService;

    public CompleteOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Order order = orderService.findById(orderId);

        orderService.completeOrder(order);

        request.getSession().setAttribute("completeSuccess", true);

        return "redirect:master?command=masterOrderDetails&orderId=" + orderId;
    }
}
