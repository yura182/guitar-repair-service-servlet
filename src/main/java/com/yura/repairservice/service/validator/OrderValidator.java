package com.yura.repairservice.service.validator;

import com.yura.repairservice.domain.order.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderValidator implements Validator<Order> {
    private static final Logger LOGGER = LogManager.getLogger(OrderValidator.class);

    @Override
    public void validate(Order order) {
        validateNotNull(order, "Order is null", LOGGER);
        validateNotNull(order.getClient(), "Client is null", LOGGER);
        validateNotNull(order.getInstrument(), "Instrument is null", LOGGER);
        validateNotNull(order.getDateTime(), "Date is null", LOGGER);
        validateNotNull(order.getService(), "Service is null", LOGGER);
        validateNotEmpty(order.getService(), "Service is empty", LOGGER);
        validateNotNull(order.getStatus(), "Status is null", LOGGER);
    }
}
