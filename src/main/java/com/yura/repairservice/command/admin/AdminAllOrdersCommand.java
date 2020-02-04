package com.yura.repairservice.command.admin;

import com.yura.repairservice.command.Command;
import com.yura.repairservice.command.PaginationUtility;
import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminAllOrdersCommand implements Command, PaginationUtility {

    private final OrderService orderService;

    public AdminAllOrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = getCurrentPage(request);
        int recordsPerPage = getRecordsPerPage(request);

        List<Order> orders = orderService.findAll(getOffset(currentPage, recordsPerPage), recordsPerPage);

        paginate(currentPage, recordsPerPage, orderService.numberOfEntries(), orders, "adminAllOrders", request, "admin");

        return "admin-all-orders.jsp";
    }
}
