package com.refugio.refugioanimal.excepciones;

import java.util.List;

public class CuidadorNoEncontradoException extends RuntimeException {
    public CuidadorNoEncontradoException(Long idCuidador) {
        super("El cuidador con ID " + idCuidador + " no existe");
    }
}
