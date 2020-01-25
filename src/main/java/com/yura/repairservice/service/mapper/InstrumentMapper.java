package com.yura.repairservice.service.mapper;

import com.yura.repairservice.domain.Instrument;
import com.yura.repairservice.entity.InstrumentEntity;

import java.util.Objects;

public class InstrumentMapper implements EntityMapper<InstrumentEntity, Instrument> {

    @Override
    public Instrument mapEntityToDomain(InstrumentEntity instrumentEntity) {
        return Objects.isNull(instrumentEntity) ? null : Instrument.builder()
                .withId(instrumentEntity.getId())
                .withBrand(instrumentEntity.getBrand())
                .withModel(instrumentEntity.getModel())
                .withYear(instrumentEntity.getManufactureYear())
                .build();
    }

    @Override
    public InstrumentEntity mapDomainToEntity(Instrument instrument) {
        return Objects.isNull(instrument) ? null : InstrumentEntity.builder()
                .withId(instrument.getId())
                .withBrand(instrument.getBrand())
                .withModel(instrument.getModel())
                .withYear(instrument.getManufactureYear())
                .build();
    }
}
