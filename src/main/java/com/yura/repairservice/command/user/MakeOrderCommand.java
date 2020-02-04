package com.yura.repairservice.command.user;

import com.yura.repairservice.command.Command;
import com.yura.repairservice.domain.instrument.Instrument;
import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.order.Status;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.exception.InvalidParameterException;
import com.yura.repairservice.service.InstrumentService;
import com.yura.repairservice.service.OrderService;

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
        Order order = Order.builder()
                .withInstrument(Instrument.builder()
                        .withBrand(request.getParameter("brand"))
                        .withModel(request.getParameter("model"))
                        .withYear(Integer.parseInt(request.getParameter("year")))
                        .build())
                .withStatus(Status.NEW)
                .withDate(LocalDateTime.now())
                .withUser((User) request.getSession().getAttribute("user"))
                .withService(request.getParameter("service"))
                .build();

        try {
            orderService.add(order);
        } catch (InvalidParameterException e) {
            request.setAttribute("errorMessage", "order.error");
            return "user-add-order.jsp";
        }

        request.getSession().setAttribute("successMessage", "order.success");

        return "redirect:user-add-order.jsp";
    }
}
