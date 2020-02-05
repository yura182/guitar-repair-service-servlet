package com.yura.repairservice.service.mapper;

import com.yura.repairservice.domain.instrument.Instrument;
import com.yura.repairservice.entity.InstrumentEntity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class InstrumentMapperTest {
    private static final Instrument INSTRUMENT = getInstrument();
    private static final InstrumentEntity INSTRUMENT_ENTITY = getInstrumentEntity();

    private final InstrumentMapper instrumentMapper = new InstrumentMapper();

    @Test
    public void mapEntityToDomainShouldReturnDomain() {
        Instrument actual = instrumentMapper.mapEntityToDomain(INSTRUMENT_ENTITY);

        assertEquals(INSTRUMENT, actual);
    }

    @Test
    public void mapDomainToEntityShouldReturnEntity() {
        InstrumentEntity actual = instrumentMapper.mapDomainToEntity(INSTRUMENT);

        assertEquals(INSTRUMENT_ENTITY, actual);
    }

    @Test
    public void mapEntityToDomainShouldReturnNull() {
        Instrument actual = instrumentMapper.mapEntityToDomain(null);

        assertNull(actual);
    }

    @Test
    public void mapDomainToEntityShouldReturnNull() {
        InstrumentEntity actual = instrumentMapper.mapDomainToEntity(null);

        assertNull(actual);
    }

    private static Instrument getInstrument() {
        return Instrument.builder()
                .withId(1)
                .withBrand("Brand")
                .withModel("Model")
                .withYear(1990)
                .build();
    }

    private static InstrumentEntity getInstrumentEntity() {
        return InstrumentEntity.builder()
                .withId(1)
                .withBrand("Brand")
                .withModel("Model")
                .withYear(1990)
                .build();
    }
}