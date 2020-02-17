package com.yura.repair.command.user;

import com.yura.repair.command.MultipleMethodCommand;

import javax.servlet.http.HttpServletRequest;

public class ProfileCommand extends MultipleMethodCommand {

    @Override
    protected String executeGet(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:/login";
        }

        return "profile";
    }

    @Override
    protected String executePost(HttpServletRequest request) {
        return null;
    }
}
