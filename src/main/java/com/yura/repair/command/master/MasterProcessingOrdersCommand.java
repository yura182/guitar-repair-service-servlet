package com.yura.repair.command.master;

import com.yura.repair.command.Command;
import com.yura.repair.command.PaginationUtility;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.dto.UserDto;
import com.yura.repair.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MasterProcessingOrdersCommand implements Command, PaginationUtility {

    private final OrderService orderService;

    public MasterProcessingOrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = getPaginationParameter(request.getParameter("currentPage"), DEFAULT_PAGINATION_PAGE);
        int recordsPerPage = getPaginationParameter(request.getParameter("recordsPerPage"), DEFAULT_PAGINATION_RECORDS);
        UserDto master = (UserDto) request.getSession().getAttribute("user");

        List<OrderDto> orders = orderService.findByMaster(master.getId(), getOffset(currentPage, recordsPerPage), recordsPerPage);

        paginate(currentPage, recordsPerPage, orderService.numberOfEntriesByMasterId(master.getId()), orders, "masterProcessingOrders", request, "master");

        return "master-all-processing-orders.jsp";
    }
}