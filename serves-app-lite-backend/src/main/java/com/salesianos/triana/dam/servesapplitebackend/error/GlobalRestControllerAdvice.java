package com.salesianos.triana.dam.servesapplitebackend.error;

import com.salesianos.triana.dam.servesapplitebackend.entity.item.exception.ItemExceptions;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.exception.ProductExceptions;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.exception.CompanyExceptions;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.exception.CustomerExceptions;
import com.salesianos.triana.dam.servesapplitebackend.error.model.ApiError;
import com.salesianos.triana.dam.servesapplitebackend.error.model.impl.ApiErrorImpl;
import com.salesianos.triana.dam.servesapplitebackend.error.model.impl.ApiValidationSubError;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalRestControllerAdvice extends ResponseEntityExceptionHandler {

    //**********************//
    //* PRODUCT EXCEPTIONS *//
    //**********************//

    @ExceptionHandler({ProductExceptions.ProductNotFoundException.class, ProductExceptions.EmptyProductListException.class})
    public ResponseEntity<?> handleProductsExceptions (EntityNotFoundException ex, WebRequest request){
        return buildApiError(ex.getMessage(), request, HttpStatus.NOT_FOUND);
    }

    //**********************//
    //* COMPANY EXCEPTIONS *//
    //**********************//

    @ExceptionHandler({CompanyExceptions.CompanyNotFoundException.class})
    public ResponseEntity<?> handleCompanyNotFoundException (EntityNotFoundException ex, WebRequest request){
        return buildApiError(ex.getMessage(), request, HttpStatus.NOT_FOUND);
    }

    //***********************//
    //* CUSTOMER EXCEPTIONS *//
    //***********************//

    @ExceptionHandler({CustomerExceptions.CustomerNotFoundException.class})
    public ResponseEntity<?> handleCustomerNotFoundException (EntityNotFoundException ex, WebRequest request){
        return buildApiError(ex.getMessage(), request, HttpStatus.NOT_FOUND);
    }

    //********************//
    //* ITEMS EXCEPTIONS *//
    //********************//

    @ExceptionHandler({ItemExceptions.ItemNotFoundException.class})
    public ResponseEntity<?> handleItemNotFoundException (EntityNotFoundException ex, WebRequest request){
        return buildApiError(ex.getMessage(), request, HttpStatus.NOT_FOUND);
    }

    private final ResponseEntity<Object> buildApiError(String message, WebRequest request, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(
                        ApiErrorImpl.builder()
                                .status(status)
                                .message(message)
                                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                                .build()
                );
    }

    private final ResponseEntity<Object> buildApiErrorWithSubErrors(String message, WebRequest request, HttpStatus status, List<ObjectError> subErrors) {
        return ResponseEntity
                .status(status)
                .body(
                        ApiErrorImpl.builder()
                                .status(status)
                                .message(message)
                                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                                .subErrors(subErrors.stream()
                                        .map(ApiValidationSubError::fromObjectError)
                                        .collect(Collectors.toList())
                                )
                                .build()
                );

    }


    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exception, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiErrorImpl.builder()
                                .status(HttpStatus.BAD_REQUEST)
                                .message("Constraint Validation error. Please check the sublist.")
                                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                                .subErrors(exception.getConstraintViolations().stream()
                                        .map(v -> {
                                            return ApiValidationSubError.builder()
                                                    .message(v.getMessage())
                                                    .rejectedValue(v.getInvalidValue())
                                                    .object(v.getRootBean().getClass().getSimpleName())
                                                    .field( ((PathImpl)v.getPropertyPath()).getLeafNode().asString())
                                                    .build();
                                        })
                                        .collect(Collectors.toList())
                                )
                                .build()
                );
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildApiError(ex.getMessage(), request, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildApiErrorWithSubErrors("Validation error. Please check the sublist.", request, status, ex.getAllErrors());
    }



}
