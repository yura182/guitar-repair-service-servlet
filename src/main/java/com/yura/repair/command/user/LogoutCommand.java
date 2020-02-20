package com.yura.repair.command.user;

import com.yura.repair.command.Command;

import javax.servlet.http.HttpServletRequest;

import static com.yura.repair.constant.PageConstant.HOME_PAGE;
import static com.yura.repair.constant.PageConstant.REDIRECT;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            request.getSession().invalidate();
        }
        return REDIRECT + HOME_PAGE;
    }
}
