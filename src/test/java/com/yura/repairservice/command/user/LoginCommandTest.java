package com.yura.repairservice.command.user;

import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.exception.UserNotFoundException;
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
public class LoginCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private UserService userService;

    @InjectMocks
    private LoginCommand command;

    @Test
    public void executeShouldReturnPage() {
        when(request.getParameter(anyString())).thenReturn("parameter");
        when(request.getSession()).thenReturn(session);
        when(userService.login(anyString(), anyString())).thenReturn(User.builder().build());

        String expected = "redirect:/";
        String actual = command.execute(request);

        verify(session).setAttribute("user", User.builder().build());
        assertEquals(expected, actual);
    }

    @Test
    public void executeShouldReturnErrorPage() {
        when(request.getParameter(anyString())).thenReturn("parameter");
        doThrow(UserNotFoundException.class).when(userService).login(anyString(), anyString());

        String expected = "login.jsp";
        String actual = command.execute(request);

        verify(request).setAttribute("errorMessage", "login.error");
        assertEquals(expected, actual);
    }
}