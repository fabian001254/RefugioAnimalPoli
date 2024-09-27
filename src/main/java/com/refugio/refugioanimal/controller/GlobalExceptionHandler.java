package com.refugio.refugioanimal.controller;

import com.refugio.refugioanimal.dto.ResponseDTO;
import com.refugio.refugioanimal.excepciones.NombreDeUsuarioException;
import com.refugio.refugioanimal.excepciones.UsuariosNoEncontradoExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NombreDeUsuarioException.class)
    public ResponseEntity<?> handleUsernameAlreadyExistsException(NombreDeUsuarioException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsuariosNoEncontradoExeption.class)
    public ResponseEntity<?> handleUserNotFoundException(UsuariosNoEncontradoExeption ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleGeneralException(Exception ex) {
        ResponseDTO response = ResponseDTO.builder()
                .mensaje("Error interno del servidor")
                .error(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
