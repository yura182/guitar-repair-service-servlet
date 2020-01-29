package com.yura.repairservice.servlet.handler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorHandlerServlet extends HttpServlet {
    private final String page;
    private final String attribute;

    public ErrorHandlerServlet(String page, String attribute) {
        this.page = page;
        this.attribute = attribute;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(attribute, true);
        req.getRequestDispatcher("/" + page + ".jsp").forward(req, resp);
    }
}
