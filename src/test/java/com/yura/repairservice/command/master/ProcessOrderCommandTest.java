package com.yura.repairservice.command.master;

import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
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
        when(orderService.findById(anyInt())).thenReturn(Order.builder().build());
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(User.builder().build());

        String expected = "redirect:master?command=masterOrderDetails&orderId=1";
        String actual = command.execute(request);

        verify(session).setAttribute("successMessage", "process.success");

        assertEquals(expected, actual);
    }
}