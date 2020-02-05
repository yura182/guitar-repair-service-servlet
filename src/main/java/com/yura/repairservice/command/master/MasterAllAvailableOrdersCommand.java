package com.yura.repairservice.command.master;

import com.yura.repairservice.command.Command;
import com.yura.repairservice.command.PaginationUtility;
import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.order.Status;
import com.yura.repairservice.service.OrderService;

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

        List<Order> orders = orderService.findByStatus(Status.ACCEPTED, getOffset(currentPage, recordsPerPage), recordsPerPage);
        paginate(currentPage, recordsPerPage, orderService.numberOfEntriesByStatus(Status.ACCEPTED), orders, "masterAllAvailableOrders", request, "master");

        return "master-all-available-orders.jsp";
    }
}
