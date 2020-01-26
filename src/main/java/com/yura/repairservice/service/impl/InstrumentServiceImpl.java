package com.yura.repairservice.service.impl;

import com.yura.repairservice.domain.instrument.Instrument;
import com.yura.repairservice.entity.InstrumentEntity;
import com.yura.repairservice.repository.InstrumentRepository;
import com.yura.repairservice.service.InstrumentService;
import com.yura.repairservice.service.mapper.EntityMapper;
import com.yura.repairservice.service.validator.Validator;

public class InstrumentServiceImpl implements InstrumentService {

    private final InstrumentRepository repository;
    private final EntityMapper<InstrumentEntity, Instrument> mapper;
    private final Validator<Instrument> validator;

    public InstrumentServiceImpl(InstrumentRepository repository, EntityMapper<InstrumentEntity, Instrument> mapper, Validator<Instrument> validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public void add(Instrument instrument) {
        validator.validate(instrument);

        repository.save(mapper.mapDomainToEntity(instrument));
    }
}
