package com.rum.orderservice.config;

import com.rum.orderservice.dto.ApiError;
import com.rum.orderservice.exception.ResourceNotFoundException;
import com.rum.orderservice.exception.UnauthorizedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setup() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    @DisplayName("Should return BAD_REQUEST status when general exception occurs")
    void shouldReturnBadRequestStatusWhenGeneralExceptionOccurs() {
        Exception exception = new Exception("General exception");

        ResponseEntity<ApiError> response = globalExceptionHandler.handleValidationExceptions(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("General exception", response.getBody().getMessage());
    }

    @Test
    @DisplayName("Should return NOT_FOUND status when ResourceNotFoundException occurs")
    void shouldReturnNotFoundStatusWhenResourceNotFoundExceptionOccurs() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Resource not found");

        ResponseEntity<ApiError> response = globalExceptionHandler.handleValidationExceptions(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Resource not found", response.getBody().getMessage());
    }

    @Test
    @DisplayName("Should return UNAUTHORIZED status when UnauthorizedException occurs")
    void shouldReturnUnauthorizedStatusWhenUnauthorizedExceptionOccurs() {
        UnauthorizedException exception = new UnauthorizedException("Unauthorized access");

        ResponseEntity<ApiError> response = globalExceptionHandler.handleValidationExceptions(exception);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Unauthorized access", response.getBody().getMessage());
    }
}