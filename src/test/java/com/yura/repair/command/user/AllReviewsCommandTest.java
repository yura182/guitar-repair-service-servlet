package com.yura.repair.command.user;

import com.yura.repair.service.ReviewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AllReviewsCommandTest {
    @Mock
    private ReviewService reviewService;

    @Mock
    HttpServletRequest request;


    @InjectMocks
    private AllReviewsCommand command;

    @Test
    public void executeShouldReturnPage() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(reviewService.findAll(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(reviewService.numberOfEntries()).thenReturn(5);

        String expected = "user-reviews.jsp";
        String actual = command.execute(request);

        assertEquals(expected, actual);

    }
}