package com.yura.repair.command.admin;

import com.yura.repair.dto.OrderDto;
import com.yura.repair.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AcceptOrderCommandTest {
    @Mock
    private OrderService orderService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private AcceptOrderCommand command;

    @Test
    public void executeShouldReturnPage() {
        when(request.getParameter("price")).thenReturn("1.1");
        when(request.getParameter("orderId")).thenReturn("1");
        when(orderService.findById(1)).thenReturn(OrderDto.builder().build());
        when(request.getSession()).thenReturn(session);

        String expected = "redirect:/admin/order-details?orderId=1";
        String actual = command.execute(request);

        verify(session).setAttribute("successMessage", "accept.success");
        assertEquals(expected, actual);
    }
}