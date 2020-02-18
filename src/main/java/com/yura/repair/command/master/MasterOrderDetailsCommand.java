package com.yura.repair.command.master;

import com.yura.repair.command.Command;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.dto.UserDto;
import com.yura.repair.service.OrderService;

import javax.servlet.http.HttpServletRequest;

import static com.yura.repair.constant.AttributeConstant.ATTR_NAME_ORDER;
import static com.yura.repair.constant.PageConstant.ERROR_PAGE;
import static com.yura.repair.constant.PageConstant.MASTER_ORDER_PAGE;

public class MasterOrderDetailsCommand implements Command {
    private final OrderService orderService;

    public MasterOrderDetailsCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        OrderDto order = orderService.findById(Integer.parseInt(request.getParameter("orderId")));
        UserDto loggedMaster = (UserDto) request.getSession().getAttribute("user");

        if (orderService.isNotMasterOrder(loggedMaster, order)) {
            return ERROR_PAGE;
        }

        request.setAttribute(ATTR_NAME_ORDER, order);

        return MASTER_ORDER_PAGE;
    }
}
