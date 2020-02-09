package com.yura.repair.service.validator;

import com.yura.repair.dto.InstrumentDto;
import com.yura.repair.exception.InvalidParameterException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class InstrumentValidatorTest {
    private final Validator<InstrumentDto> instrumentValidator = new InstrumentValidator();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void validateShouldThrowInvalidParameterExceptionForInstrumentNull() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Instrument is null");

        instrumentValidator.validate(null);
    }

    @Test
    public void validateShouldThrowInvalidParameterExceptionForBrandNull() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Brand is null");

        instrumentValidator.validate(InstrumentDto.builder().build());
    }

    @Test
    public void validateShouldThrowInvalidParameterExceptionForModelNull() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Model is null");

        instrumentValidator.validate(InstrumentDto.builder()
                .withBrand("Brand")
                .build());
    }

    @Test
    public void validateShouldThrowInvalidParameterExceptionForBrandEmpty() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Brand is empty");

        instrumentValidator.validate(InstrumentDto.builder()
                .withBrand("")
                .build());
    }

    @Test
    public void validateShouldThrowInvalidParameterExceptionForModelEmpty() {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("Model is empty");

        instrumentValidator.validate(InstrumentDto.builder()
                .withBrand("Brand")
                .withModel("")
                .build());
    }

    @Test
    public void validateShouldNotThrowException() {
        instrumentValidator.validate(InstrumentDto.builder()
                .withBrand("Brand")
                .withModel("Model")
                .build());
    }
}