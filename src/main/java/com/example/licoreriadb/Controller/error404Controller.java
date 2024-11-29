package com.example.licoreriadb.Controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

@ControllerAdvice
public class error404Controller {

    // Manejo de error 404
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Resource> handleNotFound() {
        Resource resource = new ClassPathResource("/static/404.jpg");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(resource, headers, HttpStatus.NOT_FOUND);
    }

    // Manejo de error 405
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Resource> handleMethodNotAllowed() {
        Resource resource = new ClassPathResource("/static/405.jpg");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(resource, headers, HttpStatus.METHOD_NOT_ALLOWED);
    }
}

