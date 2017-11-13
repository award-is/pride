package org.awardis.pride.error;

import lombok.extern.slf4j.Slf4j;
import org.awardis.pride.util.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EntityExistsException.class, UsernameNotFoundException.class})
    protected ResponseEntity<Object> handleInvalidRequest(Exception e) {
        Set<String> errors = new HashSet<>();
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            errors.addAll(getBindingResultErrors(exception.getBindingResult()));
        } else {
            errors.add(e.getMessage());
        }

        CustomError error = new CustomError(errors);
        for (String errorMessage : errors) {
            log.error(errorMessage);
        }

        return new ResponseEntity<>(new Response(error), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleInvalidRequest(ex);
    }

    private Set<String> getBindingResultErrors(BindingResult bindingResult) {
        Set<String> errors = new HashSet<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }
        for (ObjectError error : bindingResult.getGlobalErrors()) {
            errors.add(error.getDefaultMessage());
        }

        return errors;
    }
}

