package com.yura.repair.command.user;

import com.yura.repair.service.ReviewService;
import com.yura.repair.util.PaginationUtility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

import static com.yura.repair.constant.PageConstant.REVIEWS_PAGE;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AllReviewsCommandTest {
    @Mock
    private ReviewService reviewService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private PaginationUtility paginationUtility;

    @InjectMocks
    private AllReviewsCommand command;

    @Test
    public void executeShouldReturnPage() {
        when(reviewService.findAll(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(reviewService.numberOfEntries()).thenReturn(5);

        String expected = "reviews";
        String actual = command.execute(request);

        assertEquals(expected, actual);

    }
}