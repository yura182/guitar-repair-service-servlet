package com.yura.repair.command.master;

import com.yura.repair.command.Command;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class MasterOrderDetailsCommand implements Command {
    private final OrderService orderService;

    public MasterOrderDetailsCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        OrderDto orderDto = orderService.findById(Integer.parseInt(request.getParameter("orderId")));

        request.setAttribute("order", orderDto);

        return "master-order-details";
    }
}
