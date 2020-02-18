package com.yura.repair.command.admin;

import com.yura.repair.command.Command;
import com.yura.repair.command.helper.PaginationUtility;
import com.yura.repair.dto.ReviewDto;
import com.yura.repair.service.ReviewService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminAllReviewsCommand implements Command{
    private final ReviewService reviewService;

    private final PaginationUtility pagination;

    public AdminAllReviewsCommand(ReviewService reviewService, PaginationUtility pagination) {
        this.reviewService = reviewService;
        this.pagination = pagination;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = pagination.getCurrentPage(request);
        int recordsPerPage = pagination.getRecordsPerPage(request);

        List<ReviewDto> reviews = reviewService.findAll(pagination.getOffset(currentPage, recordsPerPage), recordsPerPage);

        pagination.paginate(currentPage, recordsPerPage, reviewService.numberOfEntries(), reviews, "/admin/reviews", request);

        return "admin-reviews";
    }
}
