package com.api.portal.exceptions;

import java.util.Date;

import com.api.portal.dto.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
 @ExceptionHandler(ClassNotFoundException.class)
 public ResponseEntity<ErrorDTO> classNotFoundException(ClassNotFoundException ex, WebRequest request) {
  ErrorDTO errorDetails = new ErrorDTO(new Date(), ex.getMessage(), HttpStatus.NOT_FOUND.toString());
  return new ResponseEntity<ErrorDTO>(errorDetails, HttpStatus.NOT_FOUND);
 }
 @ExceptionHandler(Exception.class)
 public ResponseEntity<ErrorDTO> genericServerException(Exception ex, WebRequest request) {
  ErrorDTO errorDetails = new ErrorDTO(new Date(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString());
  return new ResponseEntity<ErrorDTO>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
 }

 @ExceptionHandler(MethodArgumentNotValidException.class)
 @ResponseStatus(HttpStatus.BAD_REQUEST)
 @ResponseBody
 public ResponseEntity<ErrorDTO> methodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                     WebRequest request) {
  ErrorDTO errorDetails = new ErrorDTO(new Date(), ex.getMessage(), HttpStatus.BAD_REQUEST.toString());
  return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
 }

 @ExceptionHandler(HttpClientErrorException.Forbidden.class)
 @ResponseStatus(HttpStatus.FORBIDDEN)
 @ResponseBody
 public ResponseEntity<ErrorDTO> forbidden(MethodArgumentNotValidException ex, WebRequest request) {
  ErrorDTO errorDetails = new ErrorDTO(new Date(), ex.getMessage(), HttpStatus.FORBIDDEN.toString());
  return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
 }

 @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
 protected ResponseEntity<ErrorDTO> handleConflict(RuntimeException ex, WebRequest request) {
  ErrorDTO errorDetails = new ErrorDTO(new Date(), ex.getMessage(), HttpStatus.CONFLICT.toString());
  return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.CONFLICT);
 }

}