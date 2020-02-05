package com.yura.repairservice.service.validator;

import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.order.Review;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.exception.InvalidParameterException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDateTime;

public class ReviewValidatorTest {
    private final Validator<Review> reviewValidator = new ReviewValidator();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void validateShouldThrowInvalidParameterExceptionForReviewNull() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Review is null");

        reviewValidator.validate(null);
    }

    @Test
    public void validateShouldThrowInvalidParameterExceptionForClientNull() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Client is null");

        reviewValidator.validate(Review.builder().build());
    }

    @Test
    public void validateShouldThrowInvalidParameterExceptionForOrderNull() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Order is null");

        reviewValidator.validate(Review.builder()
                .withClient(User.builder().build())
                .build());
    }

    @Test
    public void validateShouldThrowInvalidParameterExceptionForTextNull() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Text is null");

        reviewValidator.validate(Review.builder()
                .withClient(User.builder().build())
                .withOrder(Order.builder().build())
                .build());
    }

    @Test
    public void validateShouldThrowInvalidParameterExceptionForDateNull() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Date is null");

        reviewValidator.validate(Review.builder()
                .withClient(User.builder().build())
                .withOrder(Order.builder().build())
                .withText("text")
                .build());
    }

    @Test
    public void validateShouldNotThrowException() {
        reviewValidator.validate(Review.builder()
                .withClient(User.builder().build())
                .withOrder(Order.builder().build())
                .withText("text")
                .withDate(LocalDateTime.of(1900, 12, 12, 12, 12))
                .build());
    }
}