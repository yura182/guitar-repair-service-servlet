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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProcessOrderCommandTest {
    @Mock
    private OrderService orderService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private ProcessOrderCommand command;

    @Test
    public void executeShouldReturnPage() {
        when(request.getParameter("orderId")).thenReturn("1");
        when(orderService.findById(anyInt())).thenReturn(OrderDto.builder().build());
        when(orderService.processOrder(any(OrderDto.class), any(UserDto.class))).thenReturn(true);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(UserDto.builder().build());

        String expected = "redirect:master?command=masterOrderDetails&orderId=1";
        String actual = command.execute(request);

        verify(session).setAttribute("successMessage", "process.success");

        assertEquals(expected, actual);
    }

    @Test
    public void executeShouldReturnErrorPage() {
        when(request.getParameter("orderId")).thenReturn("1");
        when(orderService.findById(anyInt())).thenReturn(OrderDto.builder().build());
        when(orderService.processOrder(any(OrderDto.class), any(UserDto.class))).thenReturn(false);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(UserDto.builder().build());

        String expected = "redirect:master?command=masterOrderDetails&orderId=1";
        String actual = command.execute(request);

        verify(session).setAttribute("errorMessage", "process.error");

        assertEquals(expected, actual);
    }
}