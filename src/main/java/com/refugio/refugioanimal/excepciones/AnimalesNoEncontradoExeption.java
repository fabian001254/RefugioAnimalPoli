package com.refugio.refugioanimal.excepciones;

import java.util.List;

public class AnimalesNoEncontradoExeption extends RuntimeException{
    public AnimalesNoEncontradoExeption(List<Long> animales) {super("Los animales "+ animales.toString() +" no existen");}
}
