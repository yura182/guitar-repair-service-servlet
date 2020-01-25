package com.yura.repairservice.service.validator;

public interface Validator<E> {

    void validate(E entity);
}
