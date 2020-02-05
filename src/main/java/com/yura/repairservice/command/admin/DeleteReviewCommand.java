package com.yura.repairservice.command.admin;

import com.yura.repairservice.command.Command;
import com.yura.repairservice.service.ReviewService;

import javax.servlet.http.HttpServletRequest;

public class DeleteReviewCommand implements Command {
    private final ReviewService reviewService;

    public DeleteReviewCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        reviewService.delete(Integer.parseInt(request.getParameter("reviewId")));

        request.getSession().setAttribute("successMessage", "all.reviews.delete.success");

        return "redirect:admin?command=adminAllReviews&recordsPerPage=3&currentPage=1";
    }
}
