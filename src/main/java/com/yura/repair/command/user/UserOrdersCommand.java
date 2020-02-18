package com.yura.repair.command.user;

import com.yura.repair.command.Command;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.dto.UserDto;
import com.yura.repair.service.OrderService;
import com.yura.repair.util.PaginationUtility;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.yura.repair.constant.PageConstant.CLIENT_ORDERS_PAGE;

public class UserOrdersCommand implements Command {
    private static final String CLIENT_ALL_ORDERS_COMMAND = "/client/all-orders";
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

        pagination.paginate(currentPage, recordsPerPage, orderService.numberOfEntriesByClientId(userDto.getId()), orders, CLIENT_ALL_ORDERS_COMMAND, request);

        return CLIENT_ORDERS_PAGE;
    }
}
