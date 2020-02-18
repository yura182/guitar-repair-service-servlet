package com.yura.repair.command.master;

import com.yura.repair.command.Command;
import com.yura.repair.command.MultipleMethodCommand;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class MasterOrderDetailsCommand extends MultipleMethodCommand {
    private final OrderService orderService;

    public MasterOrderDetailsCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    protected String executeGet(HttpServletRequest request) {
        OrderDto orderDto = orderService.findById(Integer.parseInt(request.getParameter("orderId")));

        request.setAttribute("order", orderDto);

        return "master-order-details";
    }

    @Override
    protected String executePost(HttpServletRequest request) {
        return null;
    }
}
