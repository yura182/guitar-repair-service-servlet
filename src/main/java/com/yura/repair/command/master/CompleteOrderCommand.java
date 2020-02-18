package com.yura.repair.command.master;

import com.yura.repair.command.Command;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.service.OrderService;

import javax.servlet.http.HttpServletRequest;

import static com.yura.repair.constant.AttributeConstant.ATTR_NAME_SUCCESS;
import static com.yura.repair.constant.PageConstant.MASTER_ORDER_PATH;
import static com.yura.repair.constant.PageConstant.REDIRECT;

public class CompleteOrderCommand implements Command {
    private static final String COMPLETE_SUCCESS_MESSAGE = "complete.success";

    private final OrderService orderService;

    public CompleteOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderDto orderDto = orderService.findById(orderId);

        orderService.completeOrder(orderDto);

        request.getSession().setAttribute(ATTR_NAME_SUCCESS, COMPLETE_SUCCESS_MESSAGE);

        return REDIRECT + MASTER_ORDER_PATH + orderId;
    }
}
