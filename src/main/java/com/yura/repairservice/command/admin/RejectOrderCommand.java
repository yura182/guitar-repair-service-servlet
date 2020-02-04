package com.yura.repairservice.command.admin;

import com.yura.repairservice.command.Command;
import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class RejectOrderCommand implements Command {

    private final OrderService orderService;

    public RejectOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String reason = request.getParameter("reason");
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Order order = orderService.findById(orderId);

        orderService.rejectOrder(order, reason);

        request.getSession().setAttribute("successMessage", "reject.success");

        return "redirect:admin?command=adminOrderDetails&orderId=" + orderId;
    }
}
