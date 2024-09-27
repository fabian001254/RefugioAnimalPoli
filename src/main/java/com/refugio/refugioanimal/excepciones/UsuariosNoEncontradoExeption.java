package com.refugio.refugioanimal.excepciones;

import java.util.List;

public class UsuariosNoEncontradoExeption extends RuntimeException{
    public UsuariosNoEncontradoExeption(List<Long> usuarios) {super("Los usuarios "+ usuarios.toString() +" no existen");}
}
