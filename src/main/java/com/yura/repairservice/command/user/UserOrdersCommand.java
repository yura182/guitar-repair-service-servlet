package com.yura.repairservice.command.user;

import com.yura.repairservice.command.Command;
import com.yura.repairservice.command.PaginationUtility;
import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserOrdersCommand implements Command, PaginationUtility {

    private final OrderService orderService;

    public UserOrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = getCurrentPage(request);
        int recordsPerPage = getRecordsPerPage(request);
        User user = (User) request.getSession().getAttribute("user");

        List<Order> orders = orderService.findByClient(user.getId(), getOffset(currentPage, recordsPerPage), recordsPerPage);

        paginate(currentPage, recordsPerPage, orderService.numberOfEntriesByClientId(user.getId()), orders, "userAllOrders", request, "user");

        return "user-all-orders.jsp";
    }
}
