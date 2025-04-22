package br.com.fiap.cultura.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleIntegrityViolation(){

        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("erro", "Usuário já cadastrado!");

        return new ResponseEntity<>(errorMap, HttpStatus.CONFLICT);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<Map<String, String>> handleValidation(ValidacaoException e){

        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("erro", e.getMessage());

        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);

    }
}
