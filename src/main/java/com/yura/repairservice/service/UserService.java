package com.yura.repairservice.service;

import com.yura.repairservice.domain.user.User;

import java.util.List;

public interface UserService {

    void register(User user);

    User findById(Integer id);

    User login(String email, String password);

    List<User> findAll();
}
