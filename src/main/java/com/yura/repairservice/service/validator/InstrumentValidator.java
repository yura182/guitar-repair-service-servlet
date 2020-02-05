package com.yura.repairservice.service.validator;

import com.yura.repairservice.domain.instrument.Instrument;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InstrumentValidator implements Validator<Instrument> {
    private static final Logger LOGGER = LogManager.getLogger(InstrumentValidator.class);

    @Override
    public void validate(Instrument instrument) {
        validateNotNull(instrument, "Instrument is null", LOGGER);
        validateNotNull(instrument.getBrand(), "Brand is null", LOGGER);
        validateNotEmpty(instrument.getBrand(), "Brand is empty", LOGGER);
        validateNotNull(instrument.getModel(), "Model is null", LOGGER);
        validateNotEmpty(instrument.getModel(), "Model is empty", LOGGER);
    }
}
