package com.yura.repairservice.command.admin;

import com.yura.repairservice.command.Command;
import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class AdminOrderDetailsCommand implements Command {
    private final OrderService orderService;

    public AdminOrderDetailsCommand(OrderService orderService) {
        this.orderService = orderService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        Order order = orderService.findById(Integer.parseInt(request.getParameter("orderId")));

        request.setAttribute("order", order);

        return "admin-order-details.jsp";
    }
}
