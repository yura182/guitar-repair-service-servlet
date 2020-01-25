package com.yura.repairservice.command;

import com.yura.repairservice.domain.Role;
import com.yura.repairservice.domain.User;
import com.yura.repairservice.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {
    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userService.login(email, password);
        request.getSession().setAttribute("user", user);

        Role role = user.getRole();

        if (role == Role.ADMIN) {
            return "/admin.jsp";
        } else if (role == Role.CLIENT) {
            return "/user.jsp";
        } else {
            return "/index.jsp";
        }
    }
}
