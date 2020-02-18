package com.yura.repair.command.master;

import com.yura.repair.command.Command;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.dto.UserDto;
import com.yura.repair.service.OrderService;
import com.yura.repair.util.PaginationUtility;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.yura.repair.constant.PageConstant.MASTER_PROCESSING_ORDERS_PAGE;

public class MasterProcessingOrdersCommand implements Command {
    private static final String MASTER_MY_ORDERS_COMMAND = "/master/my-orders";

    private final OrderService orderService;

    private final PaginationUtility pagination;

    public MasterProcessingOrdersCommand(OrderService orderService, PaginationUtility pagination) {
        this.orderService = orderService;
        this.pagination = pagination;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = pagination.getCurrentPage(request);
        int recordsPerPage = pagination.getRecordsPerPage(request);
        UserDto master = (UserDto) request.getSession().getAttribute("user");

        List<OrderDto> orders = orderService.findByMaster(master.getId(), pagination.getOffset(currentPage, recordsPerPage), recordsPerPage);

        pagination.paginate(currentPage, recordsPerPage, orderService.numberOfEntriesByMasterId(master.getId()), orders, MASTER_MY_ORDERS_COMMAND, request);

        return MASTER_PROCESSING_ORDERS_PAGE;
    }
}
