package com.yura.repair.command.master;

import com.yura.repair.dto.OrderDto;
import com.yura.repair.dto.UserDto;
import com.yura.repair.service.OrderService;
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
public class MasterOrderDetailsCommandTest {
    @Mock
    private OrderService orderService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private MasterOrderDetailsCommand command;

    @Test
    public void executeShouldReturnPage() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(request.getSession()).thenReturn(session);
        when(orderService.findById(anyInt())).thenReturn(OrderDto.builder().build());

        String expected = "master-order-details";
        String actual = command.execute(request);

        verify(request).setAttribute("order", OrderDto.builder().build());
        assertEquals(expected, actual);
    }

    @Test
    public void executeShouldReturnErrorPage() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(request.getSession()).thenReturn(session);
        when(orderService.findById(anyInt())).thenReturn(OrderDto.builder().build());
        when(session.getAttribute("user")).thenReturn(UserDto.builder().build());
        when(orderService.isNotMasterOrder(any(UserDto.class), any(OrderDto.class))).thenReturn(true);

        String expected = "404";
        String actual = command.execute(request);

        assertEquals(expected, actual);
    }
}