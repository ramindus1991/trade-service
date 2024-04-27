package com.rum.orderservice.exception;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String unauthorizedAccess) {
        super(unauthorizedAccess);
    }
}
