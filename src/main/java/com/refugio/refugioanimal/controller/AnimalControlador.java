package com.refugio.refugioanimal.controller;

import com.refugio.refugioanimal.dto.AnimalDTO;
import com.refugio.refugioanimal.dto.ListaCuidadores;
import com.refugio.refugioanimal.dto.ResponseDTO;
import com.refugio.refugioanimal.dto.UsuarioDetailDTO;
import com.refugio.refugioanimal.dto.usuario.ListaAnimales;
import com.refugio.refugioanimal.dto.usuario.ListaDeCuidadores;
import com.refugio.refugioanimal.dto.usuario.UsuarioDTO;
import com.refugio.refugioanimal.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/animal")
public class AnimalControlador {

    @Autowired
    private AnimalService animalService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearAnimal(@RequestBody AnimalDTO animalDTO) {
        animalService.crearAnimal(animalDTO);
        return ResponseEntity.ok().body(ResponseDTO.builder().mensaje("Animal creado exitosamente").build());
    }

    @PostMapping("/asignarCuidador/{idAnimal}")
    public ResponseEntity<?> asignarCuidador(@PathVariable Long idAnimal, @RequestBody ListaDeCuidadores cuidadores) {
        List<UsuarioDetailDTO> usuariosRepetidos = animalService.asignarCuidador(idAnimal, cuidadores);
        if (usuariosRepetidos.isEmpty()) {
            return ResponseEntity.ok().body(ResponseDTO.builder().mensaje("Cuidadores asignados exitosamente").build());
        } else {
            return ResponseEntity.ok().body(ResponseDTO.builder()
                    .mensaje("Algunos cuidadores ya estaban asignados al animal")
                    .detalles(usuariosRepetidos)
                    .build());
        }
    }

    @GetMapping("{id}/cuidadores")
    public ResponseEntity<?> obtenerCuidadores(@PathVariable Long id) {
        ListaCuidadores listaDeCuidadores = animalService.obtenerListaDeCuidadores(id);
        if(listaDeCuidadores != null) {
            return ResponseEntity.ok()
                    .body(ResponseDTO.builder()
                            .listaDeCuidadores(listaDeCuidadores).build());
        }
        return ResponseEntity.notFound().build();
    }

}
