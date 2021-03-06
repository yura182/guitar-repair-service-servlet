package com.yura.repair.command.user;

import com.yura.repair.command.Command;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.dto.ReviewDto;
import com.yura.repair.dto.UserDto;
import com.yura.repair.service.ReviewService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static com.yura.repair.constant.AttributeConstant.ATTR_NAME_SUCCESS;
import static com.yura.repair.constant.PageConstant.CLIENT_ORDER_PATH;
import static com.yura.repair.constant.PageConstant.REDIRECT;

public class LeaveReviewCommand implements Command {
    private static final String ORDER_DETAILS_REVIEW_SUCCESS_MESSAGE = "user.order.details.review.success";

    private final ReviewService reviewService;

    public LeaveReviewCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        UserDto userDto = (UserDto) request.getSession().getAttribute("user");

        OrderDto orderDto = OrderDto.builder()
                .withId(Integer.parseInt(request.getParameter("orderId")))
                .build();

        ReviewDto reviewDto = ReviewDto.builder()
                .withClient(userDto)
                .withOrder(orderDto)
                .withDate(LocalDateTime.now())
                .withText(request.getParameter("text"))
                .build();

        reviewService.add(reviewDto);

        request.getSession().setAttribute(ATTR_NAME_SUCCESS, ORDER_DETAILS_REVIEW_SUCCESS_MESSAGE);

        return REDIRECT + CLIENT_ORDER_PATH + orderDto.getId();
    }
}
