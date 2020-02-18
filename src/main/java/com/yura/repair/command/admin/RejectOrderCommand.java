package com.yura.repair.command.admin;

import com.yura.repair.command.Command;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.service.OrderService;

import javax.servlet.http.HttpServletRequest;

import static com.yura.repair.constant.AttributeConstant.ATTR_NAME_SUCCESS;
import static com.yura.repair.constant.PageConstant.ADMIN_ORDER_PATH;
import static com.yura.repair.constant.PageConstant.REDIRECT;

public class RejectOrderCommand implements Command {
    private static final String REJECT_SUCCESS_MESSAGE = "reject.success";

    private final OrderService orderService;

    public RejectOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String reason = request.getParameter("reason");
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderDto orderDto = orderService.findById(orderId);

        orderService.rejectOrder(orderDto, reason);

        request.getSession().setAttribute(ATTR_NAME_SUCCESS, REJECT_SUCCESS_MESSAGE);

        return REDIRECT + ADMIN_ORDER_PATH + orderId;
    }
}
