package com.sergeyk.course.ws.exceptions;

import com.sergeyk.course.ws.ui.model.response.CustomErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AppExceptionsHandler {

    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception exception, WebRequest request){

        String errorMessage = exception.getLocalizedMessage();
        if (errorMessage == null) {
            errorMessage = exception.toString();
        }
        CustomErrorMessage customError = new CustomErrorMessage(new Date(), errorMessage);

        return new ResponseEntity<>(
            customError,
            new HttpHeaders(),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
