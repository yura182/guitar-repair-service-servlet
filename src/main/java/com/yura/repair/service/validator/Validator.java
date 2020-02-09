package com.yura.repair.service.validator;

import com.yura.repair.exception.InvalidParameterException;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public interface Validator<E> {

    void validate(E entity);

    default <T> void validateNotNull(T parameter, String message, Logger logger) {
        if (Objects.isNull(parameter)) {
            logger.warn(message);
            throw new InvalidParameterException(message);
        }
    }

    default void validateNotEmpty(String parameter, String message, Logger logger) {
        if (parameter.isEmpty()) {
            logger.warn(message);
            throw new InvalidParameterException(message);
        }
    }
}
