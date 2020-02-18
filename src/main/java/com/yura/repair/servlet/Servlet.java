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

@WebServlet(urlPatterns = {"/login", "/register", "/profile", "/reviews", "/logout", "/client/*", "/admin/*", "/master/*"})
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
//        String command = req.getParameter("command");
        String page = commandNameToCommand.getOrDefault(getPath(req), request -> "404").execute(req);

        if (page.contains("redirect:")) {
            resp.sendRedirect(page.replaceAll("redirect:", ""));
        } else {
            req.getRequestDispatcher(resolvePath(page)).forward(req, resp);
        }
    }

    private String resolvePath(String path) {
        System.out.println("/WEB-INF/pages/" + path + ".jsp");
        return "/WEB-INF/pages/" + path + ".jsp";
    }

    private String getPath(HttpServletRequest req) {
        System.out.println(req.getRequestURI());
        return req.getRequestURI();
    }
}
