package com.yura.repairservice.servlet;

import com.yura.repairservice.context.ApplicationContextInjector;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/login", "/register", "/logout", "/make-order", "/user", "/order-details", "/leave-review", "/reviews"})
public class UserServlet extends AbstractServlet {
    public UserServlet() {
        super(ApplicationContextInjector.getInstance().getUserCommand());
    }
}
