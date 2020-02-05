package com.yura.repairservice.command.admin;

import com.yura.repairservice.service.ReviewService;
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
public class DeleteReviewCommandTest {
    @Mock
    private ReviewService reviewService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private DeleteReviewCommand command;

    @Test
    public void executeShouldReturnPage() {
        when(request.getParameter("reviewId")).thenReturn("1");
        when(request.getSession()).thenReturn(session);

        String expected = "redirect:admin?command=adminAllReviews&recordsPerPage=3&currentPage=1";
        String actual = command.execute(request);

        verify(session).setAttribute("successMessage", "all.reviews.delete.success");
        assertEquals(expected, actual);
    }
}