package com.yura.repair.servlet;

import com.yura.repair.context.ApplicationContextInjector;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/master", "/complete-order", "/process-order"})
public class MasterServlet extends AbstractServlet {
    public MasterServlet() {
        super(ApplicationContextInjector.getInstance().getMasterCommand());
    }
}
