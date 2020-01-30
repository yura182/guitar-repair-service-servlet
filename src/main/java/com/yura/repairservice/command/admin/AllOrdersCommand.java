package com.yura.repairservice.command.admin;

import com.yura.repairservice.command.Command;
import com.yura.repairservice.command.PaginationUtility;
import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllOrdersCommand implements Command, PaginationUtility {

    private final OrderService orderService;

    public AllOrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = getPaginationParameter(request.getParameter("currentPage"), DEFAULT_PAGINATION_PAGE);
        int recordsPerPage = getPaginationParameter(request.getParameter("recordsPerPage"), DEFAULT_PAGINATION_RECORDS);

        List<Order> orders = orderService.findAll(currentPage, recordsPerPage);
        paginate(currentPage, recordsPerPage, orderService.numberOfEntries(), orders, "adminAllOrders", request, "admin");

        return "admin-all-orders.jsp";
    }
}
