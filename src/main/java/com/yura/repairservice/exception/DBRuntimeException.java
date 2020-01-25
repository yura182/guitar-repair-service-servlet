package com.yura.repairservice.exception;

import java.sql.SQLException;

public class DBRuntimeException extends RuntimeException {
    public DBRuntimeException() {
    }

    public DBRuntimeException(String message) {
        super(message);
    }

    public DBRuntimeException(String message, Throwable cause) {
    }
}
