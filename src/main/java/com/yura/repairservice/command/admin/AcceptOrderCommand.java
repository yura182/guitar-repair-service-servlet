package com.yura.repairservice.command.admin;

import com.yura.repairservice.command.Command;
import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.service.OrderService;

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
        Order order = orderService.findById(orderId);

        orderService.acceptOrder(order, price);

        request.getSession().setAttribute("acceptSuccess", true);

        return "redirect:admin?command=adminOrderDetails&orderId=" + orderId;
    }
}
