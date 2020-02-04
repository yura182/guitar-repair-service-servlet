package com.yura.repairservice.servlet.handler;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/loginError")
public class LoginErrorHandler extends ErrorHandlerServlet {
    public LoginErrorHandler() {
        super("login", "errorMessage", "login.error");
    }
}
