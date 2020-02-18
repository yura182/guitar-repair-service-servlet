package com.yura.repair.command.admin;

import com.yura.repair.command.Command;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.service.OrderService;

import javax.servlet.http.HttpServletRequest;

import static com.yura.repair.constant.AttributeConstant.ATTR_NAME_ORDER;
import static com.yura.repair.constant.PageConstant.ADMIN_ORDER_PAGE;

public class AdminOrderDetailsCommand implements Command {
    private final OrderService orderService;

    public AdminOrderDetailsCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        OrderDto orderDto = orderService.findById(Integer.parseInt(request.getParameter("orderId")));

        request.setAttribute(ATTR_NAME_ORDER, orderDto);

        return ADMIN_ORDER_PAGE;
    }
}
