package com.yura.repairservice.command.user;

import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.exception.AlreadyRegisteredUserException;
import com.yura.repairservice.exception.InvalidUserParameterException;
import com.yura.repairservice.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegisterCommandTest {
    @Mock
    private UserService userService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private RegisterCommand command;

    @Test
    public void executeShouldReturnPage() {
        when(request.getParameter(anyString())).thenReturn("param");
        when(request.getSession()).thenReturn(session);
        String expected = "redirect:login.jsp";
        String actual = command.execute(request);

        verify(request, times(5)).getParameter(anyString());
        verify(session).setAttribute("successMessage", "login.just.registered");
        assertEquals(expected, actual);
    }

    @Test
    public void executeShouldReturnErrorPageForWrongParameter() {
        when(request.getParameter(anyString())).thenReturn("param");
        doThrow(InvalidUserParameterException.class).when(userService).register(any(User.class));

        String expected = "register.jsp";
        String actual = command.execute(request);

        verify(request).setAttribute("errorMessage", "register.error");
        assertEquals(expected, actual);
    }

    @Test
    public void executeShouldReturnErrorPageForExistingEmail() {
        when(request.getParameter(anyString())).thenReturn("param");
        doThrow(AlreadyRegisteredUserException.class).when(userService).register(any(User.class));

        String expected = "register.jsp";
        String actual = command.execute(request);

        verify(request).setAttribute("errorMessage", "register.error.already.registered");
        assertEquals(expected, actual);
    }
}