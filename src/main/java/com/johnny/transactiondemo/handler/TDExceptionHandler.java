package com.johnny.transactiondemo.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.johnny.transactiondemo.response.ApiResponse;

@ControllerAdvice
public class TDExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse> processUnmergeException(final MethodArgumentNotValidException ex) {
       List<String> list = ex.getBindingResult().getAllErrors().stream()
               .map(fieldError -> fieldError.getDefaultMessage())
               .collect(Collectors.toList());
        ApiResponse res = ApiResponse.builder()
            .message("fail")
            .data(list)
            .build();
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}
