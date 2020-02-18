package com.yura.repair.command.admin;

import com.yura.repair.command.Command;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.service.OrderService;

import javax.servlet.http.HttpServletRequest;

import static com.yura.repair.constant.AttributeConstant.ATTR_NAME_SUCCESS;
import static com.yura.repair.constant.PageConstant.ADMIN_ORDER_PATH;
import static com.yura.repair.constant.PageConstant.REDIRECT;

public class AcceptOrderCommand implements Command {
    private static final String ACCEPT_SUCCESS_MESSAGE = "accept.success";

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

        request.getSession().setAttribute(ATTR_NAME_SUCCESS, ACCEPT_SUCCESS_MESSAGE);

        return REDIRECT + ADMIN_ORDER_PATH + orderId;
    }
}
