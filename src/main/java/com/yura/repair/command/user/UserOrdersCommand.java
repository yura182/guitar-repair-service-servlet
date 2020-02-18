package com.yura.repair.command.user;

import com.yura.repair.command.Command;
import com.yura.repair.command.helper.PaginationUtility;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.dto.UserDto;
import com.yura.repair.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserOrdersCommand implements Command {
    private final OrderService orderService;

    private final PaginationUtility pagination;

    public UserOrdersCommand(OrderService orderService, PaginationUtility pagination) {
        this.orderService = orderService;
        this.pagination = pagination;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = pagination.getCurrentPage(request);
        int recordsPerPage = pagination.getRecordsPerPage(request);
        UserDto userDto = (UserDto) request.getSession().getAttribute("user");

        List<OrderDto> orders = orderService.findByClient(userDto.getId(), pagination.getOffset(currentPage, recordsPerPage), recordsPerPage);

        pagination.paginate(currentPage, recordsPerPage, orderService.numberOfEntriesByClientId(userDto.getId()), orders, "/client/all-orders", request);

        return "client-all-orders";
    }
}
