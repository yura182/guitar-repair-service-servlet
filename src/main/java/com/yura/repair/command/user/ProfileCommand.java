package com.yura.repair.command.user;

import com.yura.repair.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ProfileCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:/login";
        }

        return "profile";
    }
}
