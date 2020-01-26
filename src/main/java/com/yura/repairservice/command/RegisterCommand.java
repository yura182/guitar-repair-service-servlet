package com.yura.repairservice.command;

import com.yura.repairservice.domain.user.Role;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand implements Command {
    private final UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = User.builder()
                .withName(name)
                .withSurname(surname)
                .withPhone(phone)
                .withEmail(email)
                .withPassword(password)
                .withRole(Role.CLIENT)
                .build();

        userService.register(user);

        return "login";
    }
}
