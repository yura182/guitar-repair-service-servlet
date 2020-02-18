package com.yura.repair.command.user;

import com.yura.repair.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProfileCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private ProfileCommand profileCommand;

    @Test
    public void executeShouldReturnProfilePage() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(UserDto.builder().build());

        String expected = "profile";
        String actual = profileCommand.execute(request);

        assertEquals(expected, actual);
    }

    @Test
    public void executeShouldReturnLoginPageForLoggedUser() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(null);

        String expected = "redirect:login";
        String actual = profileCommand.execute(request);

        assertEquals(expected, actual);
    }
}