package com.yura.repairservice.servlet.handler;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/orderError")
public class AddOrderErrorHandler extends ErrorHandlerServlet {
    public AddOrderErrorHandler() {
        super("user-add-order", "errorMessage", "order.error");
    }
}
