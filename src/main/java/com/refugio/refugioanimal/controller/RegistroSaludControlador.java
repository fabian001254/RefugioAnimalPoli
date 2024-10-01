package com.refugio.refugioanimal.controller;

import com.refugio.refugioanimal.dto.RegistroSaludDTO;
import com.refugio.refugioanimal.dto.ResponseDTO;
import com.refugio.refugioanimal.services.RegistroSaludService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registroSalud")
public class RegistroSaludControlador {

    @Autowired
    private RegistroSaludService registroSaludService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearRegistroSalud(@RequestBody RegistroSaludDTO registroSaludDTO) {
        registroSaludService.crearRegistroSalud(registroSaludDTO);
        return ResponseEntity.ok(ResponseDTO.builder().mensaje("Registro de salud creado exitosamente").build());
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> obtenerRegistroSalud(@PathVariable Long id) {

        return ResponseEntity.ok(ResponseDTO.builder().detalles( registroSaludService.obtenerRegistrosSaludPorAnimalId(id)).build());
    }
}