package com.yura.repair.command.admin;

import com.yura.repair.command.Command;
import com.yura.repair.service.ReviewService;

import javax.servlet.http.HttpServletRequest;

import static com.yura.repair.constant.AttributeConstant.ATTR_NAME_SUCCESS;
import static com.yura.repair.constant.PageConstant.ADMIN_REVIEWS_PATH;
import static com.yura.repair.constant.PageConstant.REDIRECT;

public class DeleteReviewCommand implements Command {
    private static final String REVIEWS_DELETE_SUCCESS_MESSAGE = "all.reviews.delete.success";

    private final ReviewService reviewService;

    public DeleteReviewCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        reviewService.delete(Integer.parseInt(request.getParameter("reviewId")));

        request.getSession().setAttribute(ATTR_NAME_SUCCESS, REVIEWS_DELETE_SUCCESS_MESSAGE);

        return REDIRECT + ADMIN_REVIEWS_PATH;
    }
}
