package com.yura.repair.command.user;

import com.yura.repair.command.MultipleMethodCommand;
import com.yura.repair.dto.UserDto;
import com.yura.repair.entity.Role;
import com.yura.repair.exception.AlreadyRegisteredUserException;
import com.yura.repair.exception.InvalidUserParameterException;
import com.yura.repair.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.yura.repair.constant.AttributeConstant.ATTR_NAME_ERROR;
import static com.yura.repair.constant.AttributeConstant.ATTR_NAME_SUCCESS;
import static com.yura.repair.constant.PageConstant.*;

public class RegisterCommand extends MultipleMethodCommand {
    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);
    private static final String REGISTER_ERROR_MESSAGE = "register.error";
    private static final String ERROR_ALREADY_REGISTERED_MESSAGE = "register.error.already.registered";
    private static final String JUST_REGISTERED_MESSAGE = "login.just.registered";

    private final UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String executePost(HttpServletRequest request) {
        UserDto userDto = UserDto.builder()
                .withName(request.getParameter("name"))
                .withSurname(request.getParameter("surname"))
                .withPhone(request.getParameter("phone"))
                .withEmail(request.getParameter("email"))
                .withPassword(request.getParameter("password"))
                .withRole(Role.CLIENT)
                .build();

        try {
            userService.register(userDto);
        } catch (InvalidUserParameterException e) {
            LOGGER.warn("Validation error " + e);
            request.setAttribute(ATTR_NAME_ERROR, REGISTER_ERROR_MESSAGE);

            return REGISTER_PAGE;
        } catch (AlreadyRegisteredUserException e) {
            LOGGER.warn("User with such email already exist " + e);
            request.setAttribute(ATTR_NAME_ERROR, ERROR_ALREADY_REGISTERED_MESSAGE);

            return REGISTER_PAGE;
        }

        request.getSession().setAttribute(ATTR_NAME_SUCCESS, JUST_REGISTERED_MESSAGE);

        return REDIRECT + LOGIN_PAGE;
    }

    @Override
    protected String executeGet(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            return REDIRECT + HOME_PAGE;
        }

        return REGISTER_PAGE;
    }

}
