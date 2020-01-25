package com.yura.repairservice.servlet;

import com.yura.repairservice.command.Command;
import com.yura.repairservice.context.ApplicationContextInjector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = {"/login", "/register", "/logout"})
public class Servlet extends HttpServlet {
    private final Map<String, Command> commandNameToCommand;

    public Servlet() {
        ApplicationContextInjector context = ApplicationContextInjector.getInstance();
        this.commandNameToCommand = context.getCommand();
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
        String command = req.getParameter("command");
        String page = commandNameToCommand.get(command).execute(req, resp);

        if ("login".equals(page)) {
            resp.sendRedirect("login.jsp");
        } else {
            req.getRequestDispatcher(page).forward(req, resp);
        }

    }
}
