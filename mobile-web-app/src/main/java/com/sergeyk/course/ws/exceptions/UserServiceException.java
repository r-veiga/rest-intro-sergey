package com.sergeyk.course.ws.exceptions;

import java.io.Serializable;

public class UserServiceException extends RuntimeException {

    public UserServiceException(String message) {
        super(message);
    }
}
