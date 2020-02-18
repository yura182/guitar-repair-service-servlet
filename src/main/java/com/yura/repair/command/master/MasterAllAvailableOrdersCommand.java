package com.yura.repair.command.master;

import com.yura.repair.command.Command;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.entity.Status;
import com.yura.repair.service.OrderService;
import com.yura.repair.util.PaginationUtility;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.yura.repair.constant.PageConstant.MASTER_AVAILABLE_ORDERS_PAGE;

public class MasterAllAvailableOrdersCommand implements Command {
    private static final String MASTER_AVAILABLE_ORDERS_COMMAND = "/master/available-orders";

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
        pagination.paginate(currentPage, recordsPerPage, orderService.numberOfEntriesByStatus(Status.ACCEPTED), orders, MASTER_AVAILABLE_ORDERS_COMMAND, request);

        return MASTER_AVAILABLE_ORDERS_PAGE;
    }
}
