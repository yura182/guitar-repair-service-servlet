package com.yura.repair.command.admin;

import com.yura.repair.dto.OrderDto;
import com.yura.repair.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminOrderDetailsCommandTest {
    @Mock
    private OrderService orderService;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private AdminOrderDetailsCommand command;

    @Test
    public void executeShouldReturnPage() {
        when(request.getParameter("orderId")).thenReturn("1");
        when(orderService.findById(1)).thenReturn(OrderDto.builder().build());

        String expected = "admin-order-details";
        String actual = command.execute(request);

        verify(request).setAttribute("order", OrderDto.builder().build());
        assertEquals(expected, actual);
    }
}