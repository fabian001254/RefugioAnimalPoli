package com.refugio.refugioanimal.controller;

import com.refugio.refugioanimal.dto.ResponseDTO;
import com.refugio.refugioanimal.excepciones.AnimalesNoEncontradoExeption;
import com.refugio.refugioanimal.excepciones.CuidadorNoEncontradoException;
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

    @ExceptionHandler(AnimalesNoEncontradoExeption.class)
    public ResponseEntity<?> handleAnimalesNoEncontradoException(AnimalesNoEncontradoExeption ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseDTO.builder()
                        .mensaje("Error")
                        .datos(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(CuidadorNoEncontradoException.class)
    public ResponseEntity<?> handleCuidadorNoEncontradoException(CuidadorNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseDTO.builder()
                        .mensaje("Error")
                        .datos(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(UsuariosNoEncontradoExeption.class)
    public ResponseEntity<?> handleUsuariosNoEncontradoException(UsuariosNoEncontradoExeption ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseDTO.builder()
                        .mensaje("Error")
                        .datos(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseDTO.builder()
                        .mensaje("Error interno del servidor")
                        .datos(ex.getMessage())
                        .build());
    }
}
