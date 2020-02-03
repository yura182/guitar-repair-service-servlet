package com.yura.repairservice.command.master;

import com.yura.repairservice.command.Command;
import com.yura.repairservice.command.PaginationUtility;
import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.order.Status;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.service.OrderService;

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
        User master = (User) request.getSession().getAttribute("user");

        List<Order> orders = orderService.findByMaster(master.getId(),currentPage * recordsPerPage - recordsPerPage, recordsPerPage);
        paginate(currentPage, recordsPerPage, orderService.numberOfEntriesByMasterId(master.getId()), orders, "masterProcessingOrders", request, "master");

        return "master-all-processing-orders.jsp";
    }
}
