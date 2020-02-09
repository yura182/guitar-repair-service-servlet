package com.yura.repair.service;

import com.yura.repair.dto.UserDto;

import java.util.List;

public interface UserService {

    void register(UserDto userDto);

    UserDto findById(Integer id);

    UserDto login(String email, String password);

    List<UserDto> findAll(Integer offset, Integer limit);

    Integer numberOfEntries();
}
