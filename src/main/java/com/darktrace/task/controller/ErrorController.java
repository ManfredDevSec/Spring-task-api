package com.darktrace.task.controller;

import com.darktrace.task.domain.dto.ErrorDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

    @GetMapping("/error")
    public ResponseEntity<ErrorDto> handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String message = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        if (statusCode == null) statusCode = 500;
        if (message == null || message.isEmpty()) message = "An unexpected error occurred";

        ErrorDto errorDto = new ErrorDto(message);
        return new ResponseEntity<>(errorDto, HttpStatus.valueOf(statusCode));
    }
}