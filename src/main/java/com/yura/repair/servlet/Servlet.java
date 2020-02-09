package com.yura.repair.servlet;

import com.yura.repair.command.Command;
import com.yura.repair.context.ApplicationContextInjector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = {"/admin", "/master", "/login", "/register", "/user", "/reviews", "/logout"})
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Command> commandNameToCommand = ApplicationContextInjector.getInstance().getCommand(req.getRequestURI());
        String command = req.getParameter("command");
        System.out.println("uri " + req.getRequestURI());
        System.out.println("command" + command);
        String page = commandNameToCommand.getOrDefault(command, request -> "404.jsp").execute(req);

        if (page.contains("redirect:")) {
            resp.sendRedirect(page.replaceAll("redirect:", ""));
        } else {
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }
}
