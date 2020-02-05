package com.yura.repairservice.command.user;

import com.yura.repairservice.command.Command;
import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.order.Review;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.service.OrderService;
import com.yura.repairservice.service.ReviewService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class LeaveReviewCommand implements Command {
    private final ReviewService reviewService;

    public LeaveReviewCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");

        Order order = Order.builder()
                .withId(Integer.parseInt(request.getParameter("orderId")))
                .build();

        Review review = Review.builder()
                .withClient(user)
                .withOrder(order)
                .withDate(LocalDateTime.now())
                .withText(request.getParameter("text"))
                .build();

        reviewService.add(review);

        request.getSession().setAttribute("successMessage", "user.order.details.review.success");

        return "redirect:user?command=userOrderDetails&orderId=" + order.getId();
    }
}
