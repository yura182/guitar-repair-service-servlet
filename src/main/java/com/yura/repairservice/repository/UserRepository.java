package com.yura.repairservice.repository;

import com.yura.repairservice.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email);
}
