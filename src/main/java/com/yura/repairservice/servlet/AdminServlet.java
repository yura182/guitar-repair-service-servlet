package com.yura.repairservice.servlet;

import com.yura.repairservice.context.ApplicationContextInjector;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/admin", "/admin-order-details", "/accept-order", "/reject-order"})
public class AdminServlet extends AbstractServlet {
    public AdminServlet() {
        super(ApplicationContextInjector.getInstance().getAdminCommand());
    }
}
