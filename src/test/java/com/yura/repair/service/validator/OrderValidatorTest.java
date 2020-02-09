package com.yura.repair.service.validator;

import com.yura.repair.dto.InstrumentDto;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.entity.Status;
import com.yura.repair.dto.UserDto;
import com.yura.repair.exception.InvalidParameterException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDateTime;

public class OrderValidatorTest {
    private final Validator<OrderDto> orderValidator = new OrderValidator();

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

        orderValidator.validate(OrderDto.builder().build());
    }

    @Test
    public void validateShouldThrowInvalidParameterExceptionForInstrumentNull() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Instrument is null");

        orderValidator.validate(OrderDto.builder()
                .withUser(UserDto.builder().build())
                .build());
    }

    @Test
    public void validateShouldThrowInvalidParameterExceptionForDateNull() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Date is null");

        orderValidator.validate(OrderDto.builder()
                .withUser(UserDto.builder().build())
                .withInstrument(InstrumentDto.builder().build())
                .build());
    }

    @Test
    public void validateShouldThrowInvalidParameterExceptionForServiceNull() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Service is null");

        orderValidator.validate(OrderDto.builder()
                .withUser(UserDto.builder().build())
                .withInstrument(InstrumentDto.builder().build())
                .withDate(LocalDateTime.of(1900, 12, 12, 12, 12))
                .build());
    }

    @Test
    public void validateShouldThrowInvalidParameterExceptionForServiceEmpty() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Service is empty");

        orderValidator.validate(OrderDto.builder()
                .withUser(UserDto.builder().build())
                .withInstrument(InstrumentDto.builder().build())
                .withDate(LocalDateTime.of(1900, 12, 12, 12, 12))
                .withService("")
                .build());
    }

    @Test
    public void validateShouldThrowInvalidParameterExceptionForStatusNull() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Status is null");

        orderValidator.validate(OrderDto.builder()
                .withUser(UserDto.builder().build())
                .withInstrument(InstrumentDto.builder().build())
                .withDate(LocalDateTime.of(1900, 12, 12, 12, 12))
                .withService("Service")
                .build());
    }

    @Test
    public void validateShouldNotThrowException() {
        orderValidator.validate(OrderDto.builder()
                .withUser(UserDto.builder().build())
                .withInstrument(InstrumentDto.builder().build())
                .withDate(LocalDateTime.of(1900, 12, 12, 12, 12))
                .withService("Service")
                .withStatus(Status.NEW)
                .build());
    }
}