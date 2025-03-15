package ru.telros_test.core.exception.model;


import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ApiError {
    private HttpStatus status;
    private String reason;
    private String message;
    private LocalDateTime timestamp;

    public ApiError(HttpStatus status, String reason, String message, LocalDateTime timestamp) {
        this.status = status;
        this.reason = reason;
        this.message = message;
        this.timestamp = timestamp;
    }
}
