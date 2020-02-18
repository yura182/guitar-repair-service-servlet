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

import static com.yura.repair.constant.PageConstant.*;

public class RegisterCommand extends MultipleMethodCommand {
    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);

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
            request.setAttribute("errorMessage", "register.error");

            return REGISTER_PAGE;
        } catch (AlreadyRegisteredUserException e) {
            LOGGER.warn("User with such email already exist " + e);
            request.setAttribute("errorMessage", "register.error.already.registered");

            return REGISTER_PAGE;
        }

        request.getSession().setAttribute("successMessage", "login.just.registered");

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
