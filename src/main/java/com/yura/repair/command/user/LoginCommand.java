package com.yura.repair.command.user;

import com.yura.repair.command.MultipleMethodCommand;
import com.yura.repair.dto.UserDto;
import com.yura.repair.exception.UserNotFoundException;
import com.yura.repair.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.yura.repair.constant.AttributeConstant.ATTR_NAME_ERROR;
import static com.yura.repair.constant.PageConstant.*;

public class LoginCommand extends MultipleMethodCommand {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    private static final String LOGIN_ERROR_MESSAGE = "login.error";

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected String executeGet(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            return REDIRECT + HOME_PAGE;
        }

        return LOGIN_PAGE;
    }

    @Override
    protected String executePost(HttpServletRequest request) {
        try {
            UserDto userDto = userService.login(request.getParameter("email"), request.getParameter("password"));
            request.getSession().setAttribute("user", userDto);
        } catch (UserNotFoundException e) {
            LOGGER.warn("User not found " + e);
            request.setAttribute(ATTR_NAME_ERROR, LOGIN_ERROR_MESSAGE);

            return LOGIN_PAGE;
        }

        return REDIRECT + HOME_PAGE;
    }
}
