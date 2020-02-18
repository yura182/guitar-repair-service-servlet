package com.yura.repair.command.admin;

import com.yura.repair.command.Command;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class AcceptOrderCommand implements Command {
    private final OrderService orderService;

    public AcceptOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Double price = Double.parseDouble(request.getParameter("price"));
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderDto orderDto = orderService.findById(orderId);

        orderService.acceptOrder(orderDto, price);

        request.getSession().setAttribute("successMessage", "accept.success");

        return "redirect:/admin/order-details?orderId=" + orderId;
    }
}
