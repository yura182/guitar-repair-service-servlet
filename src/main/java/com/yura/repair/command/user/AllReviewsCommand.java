package com.yura.repair.command.user;

import com.yura.repair.command.Command;
import com.yura.repair.dto.ReviewDto;
import com.yura.repair.service.ReviewService;
import com.yura.repair.util.PaginationUtility;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.yura.repair.constant.PageConstant.REVIEWS_PAGE;

public class AllReviewsCommand implements Command {
    private final ReviewService reviewService;

    private final PaginationUtility pagination;

    public AllReviewsCommand(ReviewService reviewService, PaginationUtility pagination) {
        this.reviewService = reviewService;
        this.pagination = pagination;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = pagination.getCurrentPage(request);
        int recordsPerPage = pagination.getRecordsPerPage(request);

        List<ReviewDto> reviews = reviewService.findAll(pagination.getOffset(currentPage, recordsPerPage), recordsPerPage);

        pagination.paginate(currentPage, recordsPerPage, reviewService.numberOfEntries(), reviews, "/reviews", request);

        return REVIEWS_PAGE;
    }
}
