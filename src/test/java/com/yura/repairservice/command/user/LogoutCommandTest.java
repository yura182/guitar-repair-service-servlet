package com.yura.repairservice.command.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LogoutCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private LogoutCommand command;

    @Test
    public void executeShouldReturnPage() {
        when(request.getSession()).thenReturn(session);
        String expected = "redirect:/";
        String actual = command.execute(request);

        assertEquals(expected, actual);
    }

}