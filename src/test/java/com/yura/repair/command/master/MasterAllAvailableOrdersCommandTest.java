package com.yura.repair.command.master;

import com.yura.repair.dto.OrderDto;
import com.yura.repair.dto.UserDto;
import com.yura.repair.entity.Status;
import com.yura.repair.service.OrderService;
import com.yura.repair.util.PaginationUtility;
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

    @Mock
    private PaginationUtility paginationUtility;

    @InjectMocks
    private MasterAllAvailableOrdersCommand command;

    @Test
    public void executeShouldReturnPage() {
        when(orderService.findByStatus(any(Status.class), anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(orderService.numberOfEntriesByStatus(any(Status.class))).thenReturn(1);

        String expected = "master-all-available-orders";
        String actual = command.execute(request);

        assertEquals(expected, actual);
    }
}