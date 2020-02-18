package com.yura.repair.command.master;

import com.yura.repair.command.Command;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.service.OrderService;

import javax.servlet.http.HttpServletRequest;

import static com.yura.repair.constant.AttributeConstant.ATTR_NAME_ORDER;
import static com.yura.repair.constant.PageConstant.MASTER_ORDER_PAGE;

public class MasterOrderDetailsCommand implements Command {
    private final OrderService orderService;

    public MasterOrderDetailsCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        OrderDto orderDto = orderService.findById(Integer.parseInt(request.getParameter("orderId")));

        request.setAttribute(ATTR_NAME_ORDER, orderDto);

        return MASTER_ORDER_PAGE;
    }
}
