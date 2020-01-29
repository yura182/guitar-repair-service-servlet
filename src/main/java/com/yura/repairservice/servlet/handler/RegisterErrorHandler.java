package com.yura.repairservice.servlet.handler;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/registrationError")
public class RegisterErrorHandler extends ErrorHandlerServlet {
    public RegisterErrorHandler() {
        super("register", "registrationError");
    }
}
