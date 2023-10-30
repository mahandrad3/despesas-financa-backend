package br.com.andrad3.despesasfinancasbackend.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionResponse {

    @ExceptionHandler(value = {InvalidEnumException.class})
    protected ResponseEntity<?> handleException(InvalidEnumException ex, WebRequest webRequest){
        Map<String,Object> body = new HashMap<>();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        body.put("mensagem",ex.getMessage());
        body.put("status", status.value());
        body.put("time", LocalDateTime.now());
        if(ex.getCause() != null) body.put("erro",ex.getCause().getMessage());

        return new ResponseEntity<>(body,status);
    }
}
