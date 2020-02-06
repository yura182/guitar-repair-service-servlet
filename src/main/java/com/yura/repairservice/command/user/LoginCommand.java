package com.yura.repairservice.command.user;

import com.yura.repairservice.command.Command;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.exception.UserNotFoundException;
import com.yura.repairservice.service.UserService;
import com.yura.repairservice.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            User user = userService.login(request.getParameter("email"), request.getParameter("password"));
            request.getSession().setAttribute("user", user);
        } catch (UserNotFoundException e) {
            LOGGER.warn("User not found " + e);
            request.setAttribute("errorMessage", "login.error");

            return "login.jsp";
        }

        return "redirect:/";
    }
}
