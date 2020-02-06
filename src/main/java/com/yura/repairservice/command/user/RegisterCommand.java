package com.yura.repairservice.command.user;

import com.yura.repairservice.command.Command;
import com.yura.repairservice.domain.user.Role;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.exception.AlreadyRegisteredUserException;
import com.yura.repairservice.exception.InvalidUserParameterException;
import com.yura.repairservice.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);

    private final UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = User.builder()
                .withName(request.getParameter("name"))
                .withSurname(request.getParameter("surname"))
                .withPhone(request.getParameter("phone"))
                .withEmail(request.getParameter("email"))
                .withPassword(request.getParameter("password"))
                .withRole(Role.CLIENT)
                .build();

        try {
            userService.register(user);
        } catch (InvalidUserParameterException e) {
            LOGGER.warn("Validation error " + e);
            request.setAttribute("errorMessage", "register.error");
            return "register.jsp";
        } catch (AlreadyRegisteredUserException e) {
            LOGGER.warn("User with such email already exist " + e);
            request.setAttribute("errorMessage", "register.error.already.registered");
            return "register.jsp";
        }

        request.getSession().setAttribute("successMessage", "login.just.registered");

        return "redirect:login.jsp";
    }
}
