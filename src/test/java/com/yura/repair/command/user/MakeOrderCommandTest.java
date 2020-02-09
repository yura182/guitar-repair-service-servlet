package com.yura.repair.command.user;

import com.yura.repair.dto.OrderDto;
import com.yura.repair.dto.UserDto;
import com.yura.repair.exception.InvalidParameterException;
import com.yura.repair.service.InstrumentService;
import com.yura.repair.service.OrderService;
import org.junit.After;
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
public class MakeOrderCommandTest {
    @Mock
    private OrderService orderService;

    @Mock
    private InstrumentService instrumentService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private MakeOrderCommand command;

    @After
    public void resetMocks() {
        reset(orderService, instrumentService, request, session);
    }

    @Test
    public void executeShouldReturnPage() {
        when(request.getParameter(anyString())).thenReturn("param");
        when(request.getParameter("year")).thenReturn("1990");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(UserDto.builder().withId(1).build());

        String expected = "redirect:user-add-order.jsp";
        String actual = command.execute(request);

        verify(request, times(4)).getParameter(anyString());
        verify(session).setAttribute("successMessage", "order.success");
        assertEquals(expected, actual);
    }

    @Test
    public void executeShouldReturnPageWithError() {
        when(request.getParameter(anyString())).thenReturn("param");
        when(request.getParameter("year")).thenReturn("1990");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(UserDto.builder().withId(1).build());
        doThrow(InvalidParameterException.class).when(orderService).add(any(OrderDto.class));

        String expected = "user-add-order.jsp";
        String actual = command.execute(request);

        verify(request, times(4)).getParameter(anyString());
        verify(request).setAttribute("errorMessage", "order.error");
        assertEquals(expected, actual);
    }
}