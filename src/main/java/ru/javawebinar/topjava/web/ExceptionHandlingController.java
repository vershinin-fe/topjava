package ru.javawebinar.topjava.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.javawebinar.topjava.util.exception.NotFoundException;

@ControllerAdvice
public class ExceptionHandlingController {
    @ResponseStatus(value= HttpStatus.NOT_FOUND,
            reason="Object not found")  // 404
    @ExceptionHandler(NotFoundException.class)
    public void notFound() {
    }
}
