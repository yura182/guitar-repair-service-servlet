package com.yura.repairservice.command.admin;

import com.yura.repairservice.command.Command;
import com.yura.repairservice.command.PaginationUtility;
import com.yura.repairservice.domain.order.Review;
import com.yura.repairservice.service.ReviewService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminAllReviewsCommand implements Command, PaginationUtility {
    private final ReviewService reviewService;

    public AdminAllReviewsCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = getPaginationParameter(request.getParameter("currentPage"), DEFAULT_PAGINATION_PAGE);
        int recordsPerPage = getPaginationParameter(request.getParameter("recordsPerPage"), DEFAULT_PAGINATION_RECORDS);

        List<Review> reviews = reviewService.findAll(currentPage * recordsPerPage - recordsPerPage, recordsPerPage);

        paginate(currentPage, recordsPerPage, reviewService.numberOfEntries(), reviews, "adminAllReviews", request, "admin");

        return "admin-reviews.jsp";
    }
}
