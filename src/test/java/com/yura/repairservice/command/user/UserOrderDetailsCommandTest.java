package com.yura.repairservice.command.user;

import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.user.User;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserOrderDetailsCommandTest {
    @Mock
    private OrderService orderService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private UserOrderDetailsCommand command;

    @After
    public void resetMocks() {
        reset(orderService, request, session);
    }

    @Test
    public void executeShouldReturnPage() {
        User user = User.builder().withId(1).build();
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(anyString())).thenReturn(user);
        when(request.getParameter(anyString())).thenReturn("1");
        when(orderService.findById(anyInt())).thenReturn(Order.builder().withId(1).withUser(user).build());

        String expected = "user-order-details.jsp";
        String actual = command.execute(request);

        assertEquals(expected, actual);
    }

    @Test
    public void executeShouldReturnErrorPage() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(User.builder().withId(2).build());
        when(request.getParameter("orderId")).thenReturn("1");
        when(orderService.findById(anyInt())).thenReturn(Order.builder()
                .withId(1)
                .withUser(User.builder().withId(1).build())
                .build());

        String expected = "404.jsp";
        String actual = command.execute(request);

        assertEquals(expected, actual);
    }

    @Test
    public void executeShouldReturnErrorPageWithNullUser() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(null);
        when(request.getParameter("orderId")).thenReturn("1");
        when(orderService.findById(anyInt())).thenReturn(Order.builder()
                .withId(1)
                .withUser(User.builder().withId(1).build())
                .build());

        String expected = "404.jsp";
        String actual = command.execute(request);

        assertEquals(expected, actual);
    }

}