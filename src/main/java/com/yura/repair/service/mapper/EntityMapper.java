package com.yura.repair.service.mapper;

public interface EntityMapper<E, D> {

    D mapEntityToDomain(E entity);

    E mapDomainToEntity(D domain);
}
