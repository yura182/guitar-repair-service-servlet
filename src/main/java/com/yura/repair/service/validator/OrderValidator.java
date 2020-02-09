package com.yura.repair.service.validator;

import com.yura.repair.dto.OrderDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderValidator implements Validator<OrderDto> {
    private static final Logger LOGGER = LogManager.getLogger(OrderValidator.class);

    @Override
    public void validate(OrderDto orderDto) {
        validateNotNull(orderDto, "Order is null", LOGGER);
        validateNotNull(orderDto.getClient(), "Client is null", LOGGER);
        validateNotNull(orderDto.getInstrumentDto(), "Instrument is null", LOGGER);
        validateNotNull(orderDto.getDateTime(), "Date is null", LOGGER);
        validateNotNull(orderDto.getService(), "Service is null", LOGGER);
        validateNotEmpty(orderDto.getService(), "Service is empty", LOGGER);
        validateNotNull(orderDto.getStatus(), "Status is null", LOGGER);
    }
}
