package com.yura.repair.command.user;

import com.yura.repair.command.MultipleMethodCommand;
import com.yura.repair.dto.UserDto;
import com.yura.repair.exception.UserNotFoundException;
import com.yura.repair.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand extends MultipleMethodCommand {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected String executeGet(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            return "redirect:/";
        }

        return "login";
    }

    @Override
    protected String executePost(HttpServletRequest request) {
        try {
            UserDto userDto = userService.login(request.getParameter("email"), request.getParameter("password"));
            request.getSession().setAttribute("user", userDto);
        } catch (UserNotFoundException e) {
            LOGGER.warn("User not found " + e);
            request.setAttribute("errorMessage", "login.error");

            return "login";
        }

        return "redirect:/";
    }
}
