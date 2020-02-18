package com.yura.repair.command.master;

import com.yura.repair.command.Command;
import com.yura.repair.command.helper.PaginationUtility;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.entity.Status;
import com.yura.repair.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MasterAllAvailableOrdersCommand implements Command{
    private final OrderService orderService;

    private final PaginationUtility pagination;

    public MasterAllAvailableOrdersCommand(OrderService orderService, PaginationUtility pagination) {
        this.orderService = orderService;
        this.pagination = pagination;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = pagination.getCurrentPage(request);
        int recordsPerPage = pagination.getRecordsPerPage(request);

        List<OrderDto> orders = orderService.findByStatus(Status.ACCEPTED, pagination.getOffset(currentPage, recordsPerPage), recordsPerPage);
        pagination.paginate(currentPage, recordsPerPage, orderService.numberOfEntriesByStatus(Status.ACCEPTED), orders, "/master/available-orders", request);

        return "master-all-available-orders";
    }
}
