package com.yura.repairservice.service;

import com.yura.repairservice.domain.User;

import java.util.List;

public interface UserService {
    void register(User user);

    User login(String email, String password);

    List<User> findAll();
}
