package com.yks.cmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> unauthorizedException(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized");
    }

    @ExceptionHandler(UserExistedException.class)
    public ResponseEntity<?> userExistedException(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(StockException.class)
    public ResponseEntity<?> stockException(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("understock");
    }
}
