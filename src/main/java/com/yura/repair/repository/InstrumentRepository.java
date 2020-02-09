package com.yura.repair.repository;

import com.yura.repair.entity.InstrumentEntity;

import java.util.Optional;

public interface InstrumentRepository extends CrudRepository<InstrumentEntity, Integer> {

    Optional<Integer> saveAndReturnId(InstrumentEntity entity);
}
