package com.yura.repairservice.service.impl;

import com.yura.repairservice.domain.instrument.Instrument;
import com.yura.repairservice.entity.InstrumentEntity;
import com.yura.repairservice.exception.EntityNotFoundException;
import com.yura.repairservice.exception.UserNotFoundException;
import com.yura.repairservice.exception.EntitySavingException;
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
    public Integer add(Instrument instrument) {
        validator.validate(instrument);

        return repository
                .saveAndReturnId(mapper.mapDomainToEntity(instrument))
                .orElseThrow(() -> new EntitySavingException("Error during saving Instrument"));
    }

    @Override
    public Instrument findById(Integer id) {
        return repository
                .findById(id)
                .map(mapper::mapEntityToDomain)
                .orElseThrow(() -> new EntityNotFoundException("Instrument not found"));
    }

    @Override
    public Integer numberOfEntries() {
        return repository.countAll();
    }
}
