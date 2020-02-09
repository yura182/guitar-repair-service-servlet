package com.yura.repair.service.impl;

import com.yura.repair.dto.InstrumentDto;
import com.yura.repair.entity.InstrumentEntity;
import com.yura.repair.exception.EntityNotFoundException;
import com.yura.repair.exception.EntitySavingException;
import com.yura.repair.repository.InstrumentRepository;
import com.yura.repair.service.InstrumentService;
import com.yura.repair.service.mapper.EntityMapper;
import com.yura.repair.service.validator.Validator;

public class InstrumentServiceImpl implements InstrumentService {

    private final InstrumentRepository repository;
    private final EntityMapper<InstrumentEntity, InstrumentDto> mapper;
    private final Validator<InstrumentDto> validator;

    public InstrumentServiceImpl(InstrumentRepository repository, EntityMapper<InstrumentEntity, InstrumentDto> mapper, Validator<InstrumentDto> validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public Integer add(InstrumentDto instrumentDto) {
        validator.validate(instrumentDto);

        return repository
                .saveAndReturnId(mapper.mapDomainToEntity(instrumentDto))
                .orElseThrow(() -> new EntitySavingException("Error during saving Instrument"));
    }

    @Override
    public InstrumentDto findById(Integer id) {
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
