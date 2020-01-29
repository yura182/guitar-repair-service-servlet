package com.yura.repairservice.command;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            request.getSession().removeAttribute("user");
        }
        return "/";
    }
}
