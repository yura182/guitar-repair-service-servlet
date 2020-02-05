package com.yura.repairservice.command.user;

import com.yura.repairservice.command.Command;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = userService.login(request.getParameter("email"), request.getParameter("password"));

        request.getSession().setAttribute("user", user);

        return "redirect:/";
    }
}
