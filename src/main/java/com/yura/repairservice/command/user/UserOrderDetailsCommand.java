package com.yura.repairservice.command.user;

import com.yura.repairservice.command.Command;
import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class UserOrderDetailsCommand implements Command {
    private final OrderService orderService;

    public UserOrderDetailsCommand(OrderService orderService) {
        this.orderService = orderService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        User loggedUser = (User) request.getSession().getAttribute("user");
        Order order = orderService.findById(Integer.parseInt(request.getParameter("orderId")));

        if (loggedUser == null || !loggedUser.getId().equals(order.getClient().getId())) {
            return "404.jsp";
        }

        request.setAttribute("order", order);

        return "user-order-details.jsp";
    }
}
