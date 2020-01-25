package com.yura.repairservice.service.impl;

import com.yura.repairservice.domain.Instrument;
import com.yura.repairservice.entity.InstrumentEntity;
import com.yura.repairservice.repository.InstrumentRepository;
import com.yura.repairservice.service.InstrumentService;
import com.yura.repairservice.service.mapper.EntityMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InstrumentServiceImpl implements InstrumentService {
    private static final Logger LOGGER = LogManager.getLogger(InstrumentServiceImpl.class);

    private final InstrumentRepository repository;
    private final EntityMapper<InstrumentEntity, Instrument> mapper;

    public InstrumentServiceImpl(InstrumentRepository repository, EntityMapper<InstrumentEntity, Instrument> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void add(Instrument instrument) {
        //TODO validate
        repository.save(mapper.mapDomainToEntity(instrument));
    }
}
