package com.yura.repair.command.user;

import com.yura.repair.command.Command;
import com.yura.repair.dto.InstrumentDto;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.entity.Status;
import com.yura.repair.dto.UserDto;
import com.yura.repair.exception.InvalidParameterException;
import com.yura.repair.service.InstrumentService;
import com.yura.repair.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class MakeOrderCommand implements Command {
    public final InstrumentService instrumentService;
    public final OrderService orderService;

    public MakeOrderCommand(InstrumentService instrumentService, OrderService orderService) {
        this.instrumentService = instrumentService;
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
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
            request.setAttribute("errorMessage", "order.error");
            return "user-add-order.jsp";
        }

        request.getSession().setAttribute("successMessage", "order.success");

        return "redirect:user-add-order.jsp";
    }
}
