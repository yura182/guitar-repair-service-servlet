package com.yura.repair.command.user;

import com.yura.repair.command.Command;
import com.yura.repair.command.MultipleMethodCommand;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.dto.UserDto;
import com.yura.repair.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class UserOrderDetailsCommand extends MultipleMethodCommand {
    private final OrderService orderService;

    public UserOrderDetailsCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    protected String executeGet(HttpServletRequest request) {
        UserDto loggedUser = (UserDto) request.getSession().getAttribute("user");
        OrderDto orderDto = orderService.findById(Integer.parseInt(request.getParameter("orderId")));

        if (loggedUser == null || !loggedUser.getId().equals(orderDto.getClient().getId())) {
            return "404";
        }

        request.setAttribute("order", orderDto);

        return "client-order-details";
    }

    @Override
    protected String executePost(HttpServletRequest request) {
        return null;
    }
}
