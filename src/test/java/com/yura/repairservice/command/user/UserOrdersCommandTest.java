package com.yura.repairservice.command.user;

import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserOrdersCommandTest {
    @Mock
    private OrderService orderService;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpSession session;

    @InjectMocks
    private UserOrdersCommand userOrdersCommand;

    @Test
    public void executeShouldReturnPage() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(User.builder().withId(1).build());
        when(orderService.findByClient(anyInt(), anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(orderService.numberOfEntriesByClientId(anyInt())).thenReturn(1);

        String expectedPage = "user-all-orders.jsp";
        String actualPage = userOrdersCommand.execute(request);

        assertEquals(expectedPage, actualPage);
    }

    @Test
    public void executeShouldReturnPageWithIncorrectPaginationParameter() {
        when(request.getParameter(anyString())).thenReturn("-1");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(User.builder().withId(1).build());
        when(orderService.findByClient(anyInt(), anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(orderService.numberOfEntriesByClientId(anyInt())).thenReturn(1);

        String expectedPage = "user-all-orders.jsp";
        String actualPage = userOrdersCommand.execute(request);

        assertEquals(expectedPage, actualPage);
    }

    @Test
    public void executeShouldReturnPageWithNullPaginationParameter() {
        when(request.getParameter(anyString())).thenReturn(null);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(User.builder().withId(1).build());
        when(orderService.findByClient(anyInt(), anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(orderService.numberOfEntriesByClientId(anyInt())).thenReturn(1);

        String expectedPage = "user-all-orders.jsp";
        String actualPage = userOrdersCommand.execute(request);

        assertEquals(expectedPage, actualPage);
    }

    @Test
    public void executeShouldReturnPageWithNotNumberPaginationParameter() {
        when(request.getParameter(anyString())).thenReturn("hello");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(User.builder().withId(1).build());
        when(orderService.findByClient(anyInt(), anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(orderService.numberOfEntriesByClientId(anyInt())).thenReturn(1);

        String expectedPage = "user-all-orders.jsp";
        String actualPage = userOrdersCommand.execute(request);

        assertEquals(expectedPage, actualPage);
    }
}