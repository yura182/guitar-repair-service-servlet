package com.yura.repair.service;

import com.yura.repair.dto.InstrumentDto;

public interface InstrumentService {

    Integer add(InstrumentDto instrumentDto);

    InstrumentDto findById(Integer id);

    Integer numberOfEntries();
}
