package com.yura.repairservice.exception;

public class EntitySavingException extends RuntimeException {
    public EntitySavingException() {
    }

    public EntitySavingException(String message) {
        super(message);
    }

    public EntitySavingException(String message, Throwable cause) {
        super(message, cause);
    }
}
