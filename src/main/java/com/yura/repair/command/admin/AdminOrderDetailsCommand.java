package com.yura.repair.command.admin;

import com.yura.repair.command.Command;
import com.yura.repair.command.MultipleMethodCommand;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class AdminOrderDetailsCommand extends MultipleMethodCommand {
    private final OrderService orderService;

    public AdminOrderDetailsCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    protected String executeGet(HttpServletRequest request) {
        OrderDto orderDto = orderService.findById(Integer.parseInt(request.getParameter("orderId")));

        request.setAttribute("order", orderDto);

        return "admin-order-details";
    }

    @Override
    protected String executePost(HttpServletRequest request) {
        return null;
    }
}
