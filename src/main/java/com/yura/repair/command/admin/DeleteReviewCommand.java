package com.yura.repair.command.admin;

import com.yura.repair.command.Command;
import com.yura.repair.command.MultipleMethodCommand;
import com.yura.repair.service.ReviewService;

import javax.servlet.http.HttpServletRequest;

public class DeleteReviewCommand implements Command{
    private final ReviewService reviewService;

    public DeleteReviewCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        reviewService.delete(Integer.parseInt(request.getParameter("reviewId")));

        request.getSession().setAttribute("successMessage", "all.reviews.delete.success");

        return "redirect:/admin/reviews?recordsPerPage=3&currentPage=1";
    }
}
