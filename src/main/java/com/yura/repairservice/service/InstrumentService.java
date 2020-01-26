package com.yura.repairservice.service;

import com.yura.repairservice.domain.instrument.Instrument;

public interface InstrumentService {

    Integer add(Instrument instrument);

    Instrument findById(Integer id);
}
