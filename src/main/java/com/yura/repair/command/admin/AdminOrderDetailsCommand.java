package com.yura.repair.command.admin;

import com.yura.repair.command.Command;
import com.yura.repair.command.MultipleMethodCommand;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class AdminOrderDetailsCommand implements Command {
    private final OrderService orderService;

    public AdminOrderDetailsCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        OrderDto orderDto = orderService.findById(Integer.parseInt(request.getParameter("orderId")));

        request.setAttribute("order", orderDto);

        return "admin-order-details";
    }
}
