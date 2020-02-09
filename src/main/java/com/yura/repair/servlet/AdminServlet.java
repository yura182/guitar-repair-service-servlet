package com.yura.repair.servlet;

import com.yura.repair.context.ApplicationContextInjector;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/admin", "/accept-order", "/reject-order", "/delete-review"})
public class AdminServlet extends AbstractServlet {
    public AdminServlet() {
        super(ApplicationContextInjector.getInstance().getAdminCommand());
    }
}
