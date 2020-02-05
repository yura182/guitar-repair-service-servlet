package com.yura.repairservice.command.admin;

import com.yura.repairservice.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AllUsersCommandTest {
    @Mock
    private UserService userService;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private AllUsersCommand command;

    @Test
    public void executeShouldReturnPage() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(userService.findAll(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(userService.numberOfEntries()).thenReturn(1);

        String expected = "admin-all-users.jsp";
        String actual = command.execute(request);

        assertEquals(expected, actual);
    }
}