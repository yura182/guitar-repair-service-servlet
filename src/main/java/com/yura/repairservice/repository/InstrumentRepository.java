package com.yura.repairservice.repository;

import com.yura.repairservice.entity.InstrumentEntity;

import java.util.Optional;

public interface InstrumentRepository extends CrudRepository<InstrumentEntity, Integer> {

    Optional<Integer> saveAndReturnId(InstrumentEntity entity);
}
