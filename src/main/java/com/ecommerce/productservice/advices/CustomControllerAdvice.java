package com.ecommerce.productservice.advices;

import com.ecommerce.productservice.dtos.ErrorDTO;
import com.ecommerce.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomControllerAdvice {
    // Works as Global Exception Handler

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDTO> handleNPException(){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage("Something went wrong!");
        ResponseEntity<ErrorDTO> responseEntity
                = new ResponseEntity<>(errorDTO, HttpStatusCode.valueOf(777));

        return responseEntity;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFoundException(){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage("Product not found!");
        ResponseEntity<ErrorDTO> responseEntity =  new ResponseEntity<>(errorDTO, HttpStatusCode.valueOf(404));
        return responseEntity;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage("Something went wrong!");
        ResponseEntity<ErrorDTO> responseEntity =  new ResponseEntity<>(errorDTO, HttpStatusCode.valueOf(500));
        return responseEntity;
    }
}
