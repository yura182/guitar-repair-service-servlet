package com.yura.repair.command.user;

import com.yura.repair.command.MultipleMethodCommand;
import com.yura.repair.dto.InstrumentDto;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.dto.UserDto;
import com.yura.repair.entity.Status;
import com.yura.repair.exception.InvalidParameterException;
import com.yura.repair.service.InstrumentService;
import com.yura.repair.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static com.yura.repair.constant.AttributeConstant.ATTR_NAME_ERROR;
import static com.yura.repair.constant.AttributeConstant.ATTR_NAME_SUCCESS;
import static com.yura.repair.constant.PageConstant.*;

public class MakeOrderCommand extends MultipleMethodCommand {
    private static final String ORDER_ERROR_MESSAGE = "order.error";
    private static final String ORDER_SUCCESS_MESSAGE = "order.success";
    public final InstrumentService instrumentService;
    public final OrderService orderService;

    public MakeOrderCommand(InstrumentService instrumentService, OrderService orderService) {
        this.instrumentService = instrumentService;
        this.orderService = orderService;
    }

    @Override
    protected String executeGet(HttpServletRequest request) {
        return ADD_ORDER_PAGE;
    }

    @Override
    protected String executePost(HttpServletRequest request) {
        OrderDto orderDto = OrderDto.builder()
                .withInstrument(InstrumentDto.builder()
                        .withBrand(request.getParameter("brand"))
                        .withModel(request.getParameter("model"))
                        .withYear(Integer.parseInt(request.getParameter("year")))
                        .build())
                .withStatus(Status.NEW)
                .withDate(LocalDateTime.now())
                .withUser((UserDto) request.getSession().getAttribute("user"))
                .withService(request.getParameter("service"))
                .build();

        try {
            orderService.add(orderDto);
        } catch (InvalidParameterException e) {
            request.setAttribute(ATTR_NAME_ERROR, ORDER_ERROR_MESSAGE);
            return ADD_ORDER_PAGE;
        }

        request.getSession().setAttribute(ATTR_NAME_SUCCESS, ORDER_SUCCESS_MESSAGE);

        return REDIRECT + CLIENT_ADD_ORDER_PATH;
    }
}
