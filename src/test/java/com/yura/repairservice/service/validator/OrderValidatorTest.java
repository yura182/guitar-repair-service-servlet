package com.yura.repairservice.service.validator;

import com.yura.repairservice.domain.instrument.Instrument;
import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.order.Status;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.exception.InvalidParameterException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class OrderValidatorTest {
    private final Validator<Order> orderValidator = new OrderValidator();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void validateShouldThrowInvalidParameterExceptionForOrderNull() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Order is null");

        orderValidator.validate(null);
    }

    @Test
    public void validateShouldThrowInvalidParameterExceptionForClientNull() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Client is null");

        orderValidator.validate(Order.builder().build());
    }

    @Test
    public void validateShouldThrowInvalidParameterExceptionForInstrumentNull() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Instrument is null");

        orderValidator.validate(Order.builder()
                .withUser(User.builder().build())
                .build());
    }

    @Test
    public void validateShouldThrowInvalidParameterExceptionForDateNull() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Date is null");

        orderValidator.validate(Order.builder()
                .withUser(User.builder().build())
                .withInstrument(Instrument.builder().build())
                .build());
    }

    @Test
    public void validateShouldThrowInvalidParameterExceptionForServiceNull() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Service is null");

        orderValidator.validate(Order.builder()
                .withUser(User.builder().build())
                .withInstrument(Instrument.builder().build())
                .withDate(LocalDateTime.of(1900, 12, 12, 12, 12))
                .build());
    }

    @Test
    public void validateShouldThrowInvalidParameterExceptionForServiceEmpty() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Service is empty");

        orderValidator.validate(Order.builder()
                .withUser(User.builder().build())
                .withInstrument(Instrument.builder().build())
                .withDate(LocalDateTime.of(1900, 12, 12, 12, 12))
                .withService("")
                .build());
    }

    @Test
    public void validateShouldThrowInvalidParameterExceptionForStatusNull() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Status is null");

        orderValidator.validate(Order.builder()
                .withUser(User.builder().build())
                .withInstrument(Instrument.builder().build())
                .withDate(LocalDateTime.of(1900, 12, 12, 12, 12))
                .withService("Service")
                .build());
    }

    @Test
    public void validateShouldNotThrowException() {
        orderValidator.validate(Order.builder()
                .withUser(User.builder().build())
                .withInstrument(Instrument.builder().build())
                .withDate(LocalDateTime.of(1900, 12, 12, 12, 12))
                .withService("Service")
                .withStatus(Status.NEW)
                .build());
    }
}