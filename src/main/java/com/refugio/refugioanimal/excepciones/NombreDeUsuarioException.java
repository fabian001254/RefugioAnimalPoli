package com.refugio.refugioanimal.excepciones;

public class NombreDeUsuarioException extends RuntimeException{
    public NombreDeUsuarioException() {super("El nombre de usuario ya existe");}
}
