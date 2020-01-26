package com.yura.repairservice.service.validator;

import com.yura.repairservice.exception.InvalidInstrumentParameterException;
import com.yura.repairservice.exception.InvalidParameterException;
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
}
