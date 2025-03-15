package ru.telros_test.core.exception.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.telros_test.core.exception.EntityNotFoundException;
import ru.telros_test.core.exception.ValidationException;
import ru.telros_test.core.exception.model.ApiError;
import ru.telros_test.core.exception.ConflictException;


import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleBindException(MethodArgumentNotValidException exc) {
        //Ошибок валидации может быть несколько - возвращаем информацию по всем полям
        Map<String, String> errors = exc.getBindingResult().getFieldErrors().stream().collect(
                Collectors.toMap(FieldError::getField,
                        Objects.requireNonNull(DefaultMessageSourceResolvable::getDefaultMessage)));
        return new ApiError(
                HttpStatus.BAD_REQUEST,
                "Incorrectly made request.",
                errors.toString(),
                LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleEntityNotFoundException(final EntityNotFoundException exc) {
        return new ApiError(
                HttpStatus.NOT_FOUND,
                "The required object was not found.",
                exc.getMessage(),
                LocalDateTime.now());
    }

    @ExceptionHandler(value = {ValidationException.class, ConstraintViolationException.class,
            MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidation(final RuntimeException exc) {
        return new ApiError(
                HttpStatus.BAD_REQUEST,
                "Incorrectly made request.",
                exc.getMessage(),
                LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleValidation(final ConflictException exc) {
        return new ApiError(
                HttpStatus.CONFLICT,
                "For the requested operation the conditions are not met.",
                exc.getMessage(),
                LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleIntegrityException(DataIntegrityViolationException exc) {
        return new ApiError(
                HttpStatus.CONFLICT,
                "Integrity constraint has been violated.",
                exc.getMessage(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleThrowable(final Throwable exc) {
        return new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                exc.getClass().getName(),
                exc.getMessage(),
                LocalDateTime.now());
    }
}
