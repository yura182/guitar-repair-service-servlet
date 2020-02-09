package com.yura.repair.service.mapper;

import com.yura.repair.dto.InstrumentDto;
import com.yura.repair.entity.InstrumentEntity;

import java.util.Objects;

public class InstrumentMapper implements EntityMapper<InstrumentEntity, InstrumentDto> {

    @Override
    public InstrumentDto mapEntityToDomain(InstrumentEntity instrumentEntity) {
        return Objects.isNull(instrumentEntity) ? null : InstrumentDto.builder()
                .withId(instrumentEntity.getId())
                .withBrand(instrumentEntity.getBrand())
                .withModel(instrumentEntity.getModel())
                .withYear(instrumentEntity.getManufactureYear())
                .build();
    }

    @Override
    public InstrumentEntity mapDomainToEntity(InstrumentDto instrumentDto) {
        return Objects.isNull(instrumentDto) ? null : InstrumentEntity.builder()
                .withId(instrumentDto.getId())
                .withBrand(instrumentDto.getBrand())
                .withModel(instrumentDto.getModel())
                .withYear(instrumentDto.getManufactureYear())
                .build();
    }
}
