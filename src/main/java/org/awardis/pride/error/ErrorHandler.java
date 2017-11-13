package org.awardis.pride.error;

import com.dropbox.core.DbxException;
import lombok.extern.slf4j.Slf4j;
import org.awardis.pride.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Slf4j
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = {EntityExistsException.class, UsernameNotFoundException.class, InvalidParameterException.class})
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

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Response> handleNotFound(Exception e) {
        log.error(e.getMessage());
        CustomError error = new CustomError(e.getMessage());

        return new ResponseEntity<>(new Response(error), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DbxException.class, IOException.class, MultipartException.class})
    protected ResponseEntity<Object> handleServerError(Exception e) {
        log.error(e.getMessage());
        CustomError error = new CustomError(messageSource.getMessage("error.server.internal", null, Locale.ENGLISH));

        return new ResponseEntity<>(new Response(error), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {SecurityException.class})
    protected ResponseEntity<Object> handleForbiddenAccess(Exception e) {
        log.error(e.getMessage());
        CustomError error = new CustomError(e.getMessage());

        return new ResponseEntity<>(new Response(error), HttpStatus.FORBIDDEN);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleInvalidRequest(ex);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = messageSource.getMessage("error.request.type.not_allow", null, Locale.ENGLISH);
        CustomError error = new CustomError(message);

        return new ResponseEntity<>(new Response(error), HttpStatus.METHOD_NOT_ALLOWED);
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