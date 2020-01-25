package com.yura.repairservice.service.mapper;

public interface EntityMapper<E, D> {

    D mapEntityToDomain(E entity);

    E mapDomainToEntity(D domain);
}
