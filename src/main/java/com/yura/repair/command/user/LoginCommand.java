package com.yura.repair.command.user;

import com.yura.repair.command.Command;
import com.yura.repair.dto.UserDto;
import com.yura.repair.exception.UserNotFoundException;
import com.yura.repair.service.UserService;
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
            UserDto userDto = userService.login(request.getParameter("email"), request.getParameter("password"));
            request.getSession().setAttribute("user", userDto);
        } catch (UserNotFoundException e) {
            LOGGER.warn("User not found " + e);
            request.setAttribute("errorMessage", "login.error");

            return "login.jsp";
        }

        return "redirect:/";
    }
}
