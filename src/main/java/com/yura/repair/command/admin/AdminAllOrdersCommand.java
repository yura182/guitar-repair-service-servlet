package com.yura.repair.command.admin;

import com.yura.repair.command.Command;
import com.yura.repair.command.MultipleMethodCommand;
import com.yura.repair.command.PaginationUtility;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminAllOrdersCommand  extends MultipleMethodCommand implements PaginationUtility {

    private final OrderService orderService;

    public AdminAllOrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    protected String executeGet(HttpServletRequest request) {
        int currentPage = getCurrentPage(request);
        int recordsPerPage = getRecordsPerPage(request);

        List<OrderDto> orders = orderService.findAll(getOffset(currentPage, recordsPerPage), recordsPerPage);

        paginate(currentPage, recordsPerPage, orderService.numberOfEntries(), orders, "/admin/all-orders", request, "admin");

        return "admin-all-orders";
    }

    @Override
    protected String executePost(HttpServletRequest request) {
        return null;
    }
}
