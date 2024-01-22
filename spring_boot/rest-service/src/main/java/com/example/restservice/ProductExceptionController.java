package com.example.restservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.restservice.model.ProductDeleteException;
import com.example.restservice.model.ProductNotFoundException;

@ControllerAdvice
public class ProductExceptionController
{
    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<Object> exception(ProductNotFoundException exception)
    {
        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(value = ProductDeleteException.class)
    public ResponseEntity<Object> exception(ProductDeleteException exception)
    {
        return new ResponseEntity<>("Product deletion error", HttpStatus.NOT_ACCEPTABLE);
    }
}
