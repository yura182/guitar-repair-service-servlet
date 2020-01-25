package com.yura.repairservice.service.mapper;

import com.yura.repairservice.domain.Instrument;
import com.yura.repairservice.entity.InstrumentEntity;

import java.util.Objects;

public class InstrumentMapper {
    public Instrument mapInstrumentEntityToInstrument(InstrumentEntity instrumentEntity) {
        return Objects.isNull(instrumentEntity) ? null : Instrument.builder()
                .withId(instrumentEntity.getId())
                .withBrand(instrumentEntity.getBrand())
                .withModel(instrumentEntity.getModel())
                .withYear(instrumentEntity.getManufactureYear())
                .build();
    }

    public InstrumentEntity mapInstrumentToInstrumentEntity(Instrument instrument) {
        return Objects.isNull(instrument) ? null : InstrumentEntity.builder()
                .withId(instrument.getId())
                .withBrand(instrument.getBrand())
                .withModel(instrument.getModel())
                .withYear(instrument.getManufactureYear())
                .build();
    }
}
