package com.refugio.refugioanimal.controller;

import com.refugio.refugioanimal.dto.ListaDeAnimales;
import com.refugio.refugioanimal.dto.ResponseDTO;
import com.refugio.refugioanimal.dto.usuario.ListaDeCuidadores;
import com.refugio.refugioanimal.dto.usuario.UsuarioUpdateDTO;
import com.refugio.refugioanimal.services.AnimalService;
import com.refugio.refugioanimal.services.CuidadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cuidador")
public class CuidadorControlador {

    @Autowired
    private CuidadorService cuidadorService;

    @Autowired
    private AnimalService animalService;

    @PostMapping("/asignarAnimales/{idCuidador}")
    public ResponseEntity<?> asignarAnimales(@PathVariable Long idCuidador, @RequestBody ListaDeAnimales animales) {
        animalService.asignarAnimales(idCuidador, animales);
        return ResponseEntity.ok().body(ResponseDTO.builder().mensaje("Animales asignados exitosamente").build());
    }

    @PostMapping("/asignarCuidador/{idAnimal}")
    public ResponseEntity<?> asignarCuidador(@PathVariable Long idAnimal, @RequestBody ListaDeCuidadores cuidadores) {
        animalService.asignarCuidador(idAnimal, cuidadores);
        return ResponseEntity.ok().body(ResponseDTO.builder().mensaje("Cuidadores asignados exitosamente").build());
    }

    @PostMapping("/actualizarInformacion/{id}")
    public ResponseEntity<?> actualizarInformacion(@PathVariable Long id, @RequestBody UsuarioUpdateDTO usuarioDTO) {
        return ResponseEntity.ok()
                .body(ResponseDTO.builder()
                        .mensaje("Informacion actualizada exitosamente " + cuidadorService.actualizarInformacion(id, usuarioDTO))
                        .build());
    }

}
