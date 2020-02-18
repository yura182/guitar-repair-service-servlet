package com.yura.repair.command.master;

import com.yura.repair.command.Command;
import com.yura.repair.command.MultipleMethodCommand;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.dto.UserDto;
import com.yura.repair.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class ProcessOrderCommand implements Command {
    private final OrderService orderService;

    public ProcessOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderDto orderDto = orderService.findById(orderId);
        UserDto master = (UserDto) request.getSession().getAttribute("user");

        if (orderService.processOrder(orderDto, master)) {
            request.getSession().setAttribute("successMessage", "process.success");
        } else {
            request.getSession().setAttribute("errorMessage", "process.error");
        }

        return "redirect:/master/order-details?orderId=" + orderId;
    }
}
