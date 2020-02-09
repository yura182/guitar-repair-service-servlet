package com.yura.repair.servlet;

import com.yura.repair.context.ApplicationContextInjector;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/login", "/register", "/logout", "/make-order", "/user", "/leave-review", "/reviews"})
public class UserServlet extends AbstractServlet {
    public UserServlet() {
        super(ApplicationContextInjector.getInstance().getUserCommand());
    }
}
