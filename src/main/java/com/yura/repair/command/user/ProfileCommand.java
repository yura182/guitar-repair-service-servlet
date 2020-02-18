package com.yura.repair.command.user;

import com.yura.repair.command.Command;

import javax.servlet.http.HttpServletRequest;

import static com.yura.repair.constant.PageConstant.*;

public class ProfileCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            return REDIRECT + LOGIN_PAGE;
        }

        return PROFILE_PAGE;
    }
}
