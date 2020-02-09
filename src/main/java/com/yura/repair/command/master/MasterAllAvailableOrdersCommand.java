package com.yura.repair.command.master;

import com.yura.repair.command.Command;
import com.yura.repair.command.PaginationUtility;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.entity.Status;
import com.yura.repair.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MasterAllAvailableOrdersCommand implements Command, PaginationUtility {

    private final OrderService orderService;

    public MasterAllAvailableOrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = getCurrentPage(request);
        int recordsPerPage = getRecordsPerPage(request);

        List<OrderDto> orders = orderService.findByStatus(Status.ACCEPTED, getOffset(currentPage, recordsPerPage), recordsPerPage);
        paginate(currentPage, recordsPerPage, orderService.numberOfEntriesByStatus(Status.ACCEPTED), orders, "masterAllAvailableOrders", request, "master");

        return "master-all-available-orders.jsp";
    }
}
