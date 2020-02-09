package com.yura.repair.command.admin;

import com.yura.repair.command.Command;
import com.yura.repair.command.PaginationUtility;
import com.yura.repair.dto.ReviewDto;
import com.yura.repair.service.ReviewService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminAllReviewsCommand implements Command, PaginationUtility {
    private final ReviewService reviewService;

    public AdminAllReviewsCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = getCurrentPage(request);
        int recordsPerPage = getRecordsPerPage(request);

        List<ReviewDto> reviews = reviewService.findAll(getOffset(currentPage, recordsPerPage), recordsPerPage);

        paginate(currentPage, recordsPerPage, reviewService.numberOfEntries(), reviews, "adminAllReviews", request, "admin");

        return "admin-reviews.jsp";
    }
}
