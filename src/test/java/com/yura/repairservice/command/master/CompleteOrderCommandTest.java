package com.yura.repairservice.command.master;

import com.yura.repairservice.command.user.MakeOrderCommand;
import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.exception.InvalidParameterException;
import com.yura.repairservice.service.InstrumentService;
import com.yura.repairservice.service.OrderService;
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
public class CompleteOrderCommandTest {
    @Mock
    private OrderService orderService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private CompleteOrderCommand command;

    @Test
    public void executeShouldReturnPage() {
        when(request.getParameter("orderId")).thenReturn("1");
        when(orderService.findById(1)).thenReturn(Order.builder().build());
        when(request.getSession()).thenReturn(session);

        String expected = "redirect:master?command=masterOrderDetails&orderId=1";
        String actual = command.execute(request);

        verify(session).setAttribute("successMessage", "complete.success");
        assertEquals(expected, actual);
    }
}