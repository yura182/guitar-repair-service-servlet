package com.yura.repair.command.admin;

import com.yura.repair.command.Command;
import com.yura.repair.command.MultipleMethodCommand;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class RejectOrderCommand extends MultipleMethodCommand {
    private final OrderService orderService;

    public RejectOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    protected String executeGet(HttpServletRequest request) {
        return null;
    }

    @Override
    protected String executePost(HttpServletRequest request) {
        String reason = request.getParameter("reason");
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderDto orderDto = orderService.findById(orderId);

        orderService.rejectOrder(orderDto, reason);

        request.getSession().setAttribute("successMessage", "reject.success");

        return "redirect:/admin/order-details?orderId=" + orderId;
    }
}
