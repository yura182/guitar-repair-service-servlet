package com.yura.repair.command.admin;

import com.yura.repair.command.Command;
import com.yura.repair.command.MultipleMethodCommand;
import com.yura.repair.command.PaginationUtility;
import com.yura.repair.dto.ReviewDto;
import com.yura.repair.service.ReviewService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminAllReviewsCommand extends MultipleMethodCommand implements PaginationUtility {
    private final ReviewService reviewService;

    public AdminAllReviewsCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    protected String executeGet(HttpServletRequest request) {
        int currentPage = getCurrentPage(request);
        int recordsPerPage = getRecordsPerPage(request);

        List<ReviewDto> reviews = reviewService.findAll(getOffset(currentPage, recordsPerPage), recordsPerPage);

        paginate(currentPage, recordsPerPage, reviewService.numberOfEntries(), reviews, "/admin/all-reviews", request, "admin");

        return "admin-reviews";
    }

    @Override
    protected String executePost(HttpServletRequest request) {
        return null;
    }
}
