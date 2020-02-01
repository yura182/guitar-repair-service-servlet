package com.yura.repairservice.service.validator;

import com.yura.repairservice.domain.order.Review;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReviewValidator implements Validator<Review> {
    private static final Logger LOGGER = LogManager.getLogger(ReviewValidator.class);

    @Override
    public void validate(Review review) {
        validateNotNull(review, "Review is null", LOGGER);
        validateNotNull(review.getClient(), "Client is null", LOGGER);
        validateNotNull(review.getOrder(), "Order is null", LOGGER);
        validateNotNull(review.getText(), "Text is null", LOGGER);
        validateNotNull(review.getDate(), "Date is null", LOGGER);
    }
}
