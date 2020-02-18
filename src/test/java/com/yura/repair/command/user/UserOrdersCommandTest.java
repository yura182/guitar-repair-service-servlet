package com.yura.repair.command.user;

import com.yura.repair.dto.UserDto;
import com.yura.repair.service.OrderService;
import com.yura.repair.util.PaginationUtility;
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
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private PaginationUtility paginationUtility;

    @InjectMocks
    private UserOrdersCommand userOrdersCommand;

    @Test
    public void executeShouldReturnPage() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(UserDto.builder().withId(1).build());
        when(orderService.findByClient(anyInt(), anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(orderService.numberOfEntriesByClientId(anyInt())).thenReturn(1);

        String expectedPage = "client-all-orders";
        String actualPage = userOrdersCommand.execute(request);

        assertEquals(expectedPage, actualPage);
    }

    @Test
    public void executeShouldReturnPageWithIncorrectPaginationParameter() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(UserDto.builder().withId(1).build());
        when(orderService.findByClient(anyInt(), anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(orderService.numberOfEntriesByClientId(anyInt())).thenReturn(1);

        String expectedPage = "client-all-orders";
        String actualPage = userOrdersCommand.execute(request);

        assertEquals(expectedPage, actualPage);
    }

    @Test
    public void executeShouldReturnPageWithNullPaginationParameter() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(UserDto.builder().withId(1).build());
        when(orderService.findByClient(anyInt(), anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(orderService.numberOfEntriesByClientId(anyInt())).thenReturn(1);

        String expectedPage = "client-all-orders";
        String actualPage = userOrdersCommand.execute(request);

        assertEquals(expectedPage, actualPage);
    }

    @Test
    public void executeShouldReturnPageWithNotNumberPaginationParameter() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(UserDto.builder().withId(1).build());
        when(orderService.findByClient(anyInt(), anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(orderService.numberOfEntriesByClientId(anyInt())).thenReturn(1);

        String expectedPage = "client-all-orders";
        String actualPage = userOrdersCommand.execute(request);

        assertEquals(expectedPage, actualPage);
    }
}