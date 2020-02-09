package com.yura.repair.service.validator;

import com.yura.repair.dto.OrderDto;
import com.yura.repair.dto.ReviewDto;
import com.yura.repair.dto.UserDto;
import com.yura.repair.exception.InvalidParameterException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDateTime;

public class ReviewValidatorTest {
    private final Validator<ReviewDto> reviewValidator = new ReviewValidator();

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

        reviewValidator.validate(ReviewDto.builder().build());
    }

    @Test
    public void validateShouldThrowInvalidParameterExceptionForOrderNull() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Order is null");

        reviewValidator.validate(ReviewDto.builder()
                .withClient(UserDto.builder().build())
                .build());
    }

    @Test
    public void validateShouldThrowInvalidParameterExceptionForTextNull() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Text is null");

        reviewValidator.validate(ReviewDto.builder()
                .withClient(UserDto.builder().build())
                .withOrder(OrderDto.builder().build())
                .build());
    }

    @Test
    public void validateShouldThrowInvalidParameterExceptionForDateNull() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Date is null");

        reviewValidator.validate(ReviewDto.builder()
                .withClient(UserDto.builder().build())
                .withOrder(OrderDto.builder().build())
                .withText("text")
                .build());
    }

    @Test
    public void validateShouldNotThrowException() {
        reviewValidator.validate(ReviewDto.builder()
                .withClient(UserDto.builder().build())
                .withOrder(OrderDto.builder().build())
                .withText("text")
                .withDate(LocalDateTime.of(1900, 12, 12, 12, 12))
                .build());
    }
}