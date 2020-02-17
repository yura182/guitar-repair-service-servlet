package com.yura.repair.command.user;

import com.yura.repair.command.Command;
import com.yura.repair.command.MultipleMethodCommand;
import com.yura.repair.command.PaginationUtility;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.dto.UserDto;
import com.yura.repair.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserOrdersCommand extends MultipleMethodCommand implements PaginationUtility {

    private final OrderService orderService;

    public UserOrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    protected String executeGet(HttpServletRequest request) {
        int currentPage = getCurrentPage(request);
        int recordsPerPage = getRecordsPerPage(request);
        UserDto userDto = (UserDto) request.getSession().getAttribute("user");

        List<OrderDto> orders = orderService.findByClient(userDto.getId(), getOffset(currentPage, recordsPerPage), recordsPerPage);

        paginate(currentPage, recordsPerPage, orderService.numberOfEntriesByClientId(userDto.getId()), orders, "/client/all-orders", request, "user");

        return "client-all-orders";
    }

    @Override
    protected String executePost(HttpServletRequest request) {
        return null;
    }
}
