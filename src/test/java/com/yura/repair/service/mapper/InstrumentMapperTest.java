package com.yura.repair.service.mapper;

import com.yura.repair.dto.InstrumentDto;
import com.yura.repair.entity.InstrumentEntity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class InstrumentMapperTest {
    private static final InstrumentDto INSTRUMENT_DTO_DTO = getInstrumentDtoDto();
    private static final InstrumentEntity INSTRUMENT_ENTITY = getInstrumentEntity();

    private final InstrumentMapper instrumentMapper = new InstrumentMapper();

    @Test
    public void mapEntityToDomainShouldReturnDomain() {
        InstrumentDto actual = instrumentMapper.mapEntityToDomain(INSTRUMENT_ENTITY);

        assertEquals(INSTRUMENT_DTO_DTO, actual);
    }

    @Test
    public void mapDomainToEntityShouldReturnEntity() {
        InstrumentEntity actual = instrumentMapper.mapDomainToEntity(INSTRUMENT_DTO_DTO);

        assertEquals(INSTRUMENT_ENTITY, actual);
    }

    @Test
    public void mapEntityToDomainShouldReturnNull() {
        InstrumentDto actual = instrumentMapper.mapEntityToDomain(null);

        assertNull(actual);
    }

    @Test
    public void mapDomainToEntityShouldReturnNull() {
        InstrumentEntity actual = instrumentMapper.mapDomainToEntity(null);

        assertNull(actual);
    }

    private static InstrumentDto getInstrumentDtoDto() {
        return InstrumentDto.builder()
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