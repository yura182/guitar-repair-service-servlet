package com.yura.repair.service.validator;

import com.yura.repair.dto.ReviewDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReviewValidator implements Validator<ReviewDto> {
    private static final Logger LOGGER = LogManager.getLogger(ReviewValidator.class);

    @Override
    public void validate(ReviewDto reviewDto) {
        validateNotNull(reviewDto, "Review is null", LOGGER);
        validateNotNull(reviewDto.getClient(), "Client is null", LOGGER);
        validateNotNull(reviewDto.getOrder(), "Order is null", LOGGER);
        validateNotNull(reviewDto.getText(), "Text is null", LOGGER);
        validateNotNull(reviewDto.getDate(), "Date is null", LOGGER);
    }
}
