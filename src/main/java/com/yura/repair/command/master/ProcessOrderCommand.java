package com.yura.repair.command.master;

import com.yura.repair.command.Command;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.dto.UserDto;
import com.yura.repair.exception.OrderAlreadyUpdatedException;
import com.yura.repair.service.OrderService;
import com.yura.repair.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.yura.repair.constant.AttributeConstant.ATTR_NAME_ERROR;
import static com.yura.repair.constant.AttributeConstant.ATTR_NAME_SUCCESS;
import static com.yura.repair.constant.PageConstant.*;

public class ProcessOrderCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ProcessOrderCommand.class);

    private static final String PROCESS_SUCCESS_MESSAGE = "process.success";
    private static final String PROCESS_ERROR_MESSAGE = "process.error";

    private final OrderService orderService;

    public ProcessOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderDto orderDto = orderService.findById(orderId);
        UserDto master = (UserDto) request.getSession().getAttribute("user");

        try {
            orderService.processOrder(orderDto, master);
            request.getSession().setAttribute(ATTR_NAME_SUCCESS, PROCESS_SUCCESS_MESSAGE);

            return REDIRECT + MASTER_ORDER_PATH + orderId;
        } catch (OrderAlreadyUpdatedException e) {
            LOGGER.warn("Order already processed by another master", e);
            request.getSession().setAttribute(ATTR_NAME_ERROR, PROCESS_ERROR_MESSAGE);

            return REDIRECT + MASTER_AVAILABLE_ORDERS_PATH;
        }
    }
}
