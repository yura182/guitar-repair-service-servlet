package com.yura.repair.command.user;

import com.yura.repair.command.Command;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.dto.UserDto;
import com.yura.repair.service.OrderService;

import javax.servlet.http.HttpServletRequest;

import static com.yura.repair.constant.PageConstant.ERROR_PAGE;

public class UserOrderDetailsCommand implements Command {
    private final OrderService orderService;

    public UserOrderDetailsCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        UserDto loggedUser = (UserDto) request.getSession().getAttribute("user");
        OrderDto orderDto = orderService.findById(Integer.parseInt(request.getParameter("orderId")));

        if (loggedUser == null || !loggedUser.getId().equals(orderDto.getClient().getId())) {
            return ERROR_PAGE;
        }

        request.setAttribute("order", orderDto);

        return "client-order-details";
    }
}
