package com.yura.repair.command.user;

import com.yura.repair.dto.UserDto;
import com.yura.repair.service.ReviewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LeaveReviewCommandTest {
    @Mock
    private ReviewService reviewService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private LeaveReviewCommand command;

    @Test
    public void executeShouldReturnPage() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(UserDto.builder().build());
        when(request.getParameter(anyString())).thenReturn("parameter");
        when(request.getParameter("orderId")).thenReturn("1");

        String expected = "redirect:/client/order-details?orderId=1";
        String actual = command.execute(request);

        verify(session).setAttribute("successMessage", "user.order.details.review.success");
        assertEquals(expected, actual);
    }
}