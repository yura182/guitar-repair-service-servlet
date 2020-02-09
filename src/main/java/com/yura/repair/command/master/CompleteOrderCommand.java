package com.yura.repair.command.master;

import com.yura.repair.command.Command;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class CompleteOrderCommand implements Command {

    private final OrderService orderService;

    public CompleteOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderDto orderDto = orderService.findById(orderId);

        orderService.completeOrder(orderDto);

        request.getSession().setAttribute("successMessage", "complete.success");

        return "redirect:master?command=masterOrderDetails&orderId=" + orderId;
    }
}
