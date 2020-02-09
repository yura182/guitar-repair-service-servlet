package com.yura.repair.service.validator;

import com.yura.repair.dto.InstrumentDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InstrumentValidator implements Validator<InstrumentDto> {
    private static final Logger LOGGER = LogManager.getLogger(InstrumentValidator.class);

    @Override
    public void validate(InstrumentDto instrumentDto) {
        validateNotNull(instrumentDto, "Instrument is null", LOGGER);
        validateNotNull(instrumentDto.getBrand(), "Brand is null", LOGGER);
        validateNotEmpty(instrumentDto.getBrand(), "Brand is empty", LOGGER);
        validateNotNull(instrumentDto.getModel(), "Model is null", LOGGER);
        validateNotEmpty(instrumentDto.getModel(), "Model is empty", LOGGER);
    }
}
