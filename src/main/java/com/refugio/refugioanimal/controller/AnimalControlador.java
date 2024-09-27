package com.refugio.refugioanimal.controller;

import com.refugio.refugioanimal.dto.AnimalDTO;
import com.refugio.refugioanimal.dto.ResponseDTO;
import com.refugio.refugioanimal.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
