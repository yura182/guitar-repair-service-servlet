package com.yura.repair.command.user;

import com.yura.repair.command.Command;
import com.yura.repair.command.MultipleMethodCommand;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.dto.ReviewDto;
import com.yura.repair.dto.UserDto;
import com.yura.repair.service.ReviewService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class LeaveReviewCommand extends MultipleMethodCommand {
    private final ReviewService reviewService;

    public LeaveReviewCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    protected String executeGet(HttpServletRequest request) {
        return null;
    }

    @Override
    protected String executePost(HttpServletRequest request) {
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

        request.getSession().setAttribute("successMessage", "user.order.details.review.success");

        return "redirect:/client/order-details?orderId=" + orderDto.getId();
    }
}
