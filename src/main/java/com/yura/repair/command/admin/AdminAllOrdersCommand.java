package com.yura.repair.command.admin;

import com.yura.repair.command.Command;
import com.yura.repair.command.PaginationUtility;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.service.OrderService;

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

        List<OrderDto> orders = orderService.findAll(getOffset(currentPage, recordsPerPage), recordsPerPage);

        paginate(currentPage, recordsPerPage, orderService.numberOfEntries(), orders, "adminAllOrders", request, "admin");

        return "admin-all-orders.jsp";
    }
}
