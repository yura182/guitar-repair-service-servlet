package com.yura.repairservice.servlet.handler;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/alreadyRegisteredError")
public class AlreadyRegisteredErrorHandler extends ErrorHandlerServlet {
    public AlreadyRegisteredErrorHandler() {
        super("register", "alreadyRegisteredError");
    }
}
