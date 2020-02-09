package com.yura.repair.command.user;

import com.yura.repair.command.Command;
import com.yura.repair.command.PaginationUtility;
import com.yura.repair.dto.ReviewDto;
import com.yura.repair.service.ReviewService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllReviewsCommand implements Command, PaginationUtility {
    private final ReviewService reviewService;

    public AllReviewsCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = getCurrentPage(request);
        int recordsPerPage = getRecordsPerPage(request);

        List<ReviewDto> reviews = reviewService.findAll(getOffset(currentPage, recordsPerPage), recordsPerPage);

        paginate(currentPage, recordsPerPage, reviewService.numberOfEntries(), reviews, "allReviews", request, "reviews");

        return "reviews.jsp";
    }


}
