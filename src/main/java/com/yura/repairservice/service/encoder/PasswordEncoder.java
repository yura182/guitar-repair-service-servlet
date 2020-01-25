package com.yura.repairservice.service.encoder;

import java.util.Base64;

public class PasswordEncoder {

    public String encode(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }
}
