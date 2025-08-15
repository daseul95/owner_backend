//package me.dev.controller;
//
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<String> handleInvalidJson(HttpMessageNotReadableException ex) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body("잘못된 JSON 형식입니다.");
//    }
//
//    // 다른 예외도 처리 가능
//}