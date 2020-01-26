package com.yura.repairservice.service.validator;

import com.yura.repairservice.domain.order.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommentValidator implements Validator<Comment> {
    private static final Logger LOGGER = LogManager.getLogger(CommentValidator.class);

    @Override
    public void validate(Comment comment) {
        validateNotNull(comment, "Comment is null", LOGGER);
        validateNotNull(comment.getClient(), "Client is null", LOGGER);
        validateNotNull(comment.getOrder(), "Order is null", LOGGER);
        validateNotNull(comment.getText(), "Text is null", LOGGER);
        validateNotNull(comment.getDate(), "Date is null", LOGGER);
    }
}
