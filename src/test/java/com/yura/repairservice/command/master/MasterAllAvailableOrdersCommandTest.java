package com.yura.repairservice.command.master;

import com.yura.repairservice.domain.order.Status;
import com.yura.repairservice.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MasterAllAvailableOrdersCommandTest {
    @Mock
    private OrderService orderService;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private MasterAllAvailableOrdersCommand command;

    @Test
    public void executeShouldReturnPage() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(orderService.findByStatus(any(Status.class), anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(orderService.numberOfEntriesByStatus(any(Status.class))).thenReturn(1);

        String expected = "master-all-available-orders.jsp";
        String actual = command.execute(request);

        assertEquals(expected, actual);
    }
}