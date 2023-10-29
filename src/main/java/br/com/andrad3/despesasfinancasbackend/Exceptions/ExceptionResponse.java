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

        body.put("Mensagem",ex.getMessage());
        body.put("Status", status.value());
        body.put("Mensagem",ex.getMessage());
        body.put("Time", LocalDateTime.now());

        return new ResponseEntity<>(body,status);
    }
}
