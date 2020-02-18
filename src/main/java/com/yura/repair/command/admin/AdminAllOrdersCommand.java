package com.yura.repair.command.admin;

import com.yura.repair.command.Command;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.service.OrderService;
import com.yura.repair.util.PaginationUtility;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminAllOrdersCommand implements Command {
    private final OrderService orderService;

    private final PaginationUtility pagination;

    public AdminAllOrdersCommand(PaginationUtility pagination, OrderService orderService) {
        this.pagination = pagination;
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = pagination.getCurrentPage(request);
        int recordsPerPage = pagination.getRecordsPerPage(request);

        List<OrderDto> orders = orderService.findAll(pagination.getOffset(currentPage, recordsPerPage), recordsPerPage);

        pagination.paginate(currentPage, recordsPerPage, orderService.numberOfEntries(), orders, "/admin/orders", request);

        return "admin-all-orders";
    }
}
