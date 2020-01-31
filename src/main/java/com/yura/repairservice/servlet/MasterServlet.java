package com.yura.repairservice.servlet;

import com.yura.repairservice.context.ApplicationContextInjector;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/master", "/master-order-details", "/complete-order", "/process-order"})
public class MasterServlet extends AbstractServlet {
    public MasterServlet() {
        super(ApplicationContextInjector.getInstance().getMasterCommand());
    }
}
