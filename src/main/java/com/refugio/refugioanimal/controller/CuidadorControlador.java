package com.refugio.refugioanimal.controller;

import com.refugio.refugioanimal.dto.AnimalDTO;
import com.refugio.refugioanimal.dto.ListaDeAnimales;
import com.refugio.refugioanimal.dto.ResponseDTO;
import com.refugio.refugioanimal.dto.UsuarioDetailDTO;
import com.refugio.refugioanimal.dto.usuario.ListaDeCuidadores;
import com.refugio.refugioanimal.dto.usuario.UsuarioDTO;
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
        List<AnimalDTO> animalesRepetidos = animalService.asignarAnimales(idCuidador, animales);

        if (animalesRepetidos.isEmpty()) {
            return ResponseEntity.ok().body(ResponseDTO.builder().mensaje("Todos los animales fueron asignados exitosamente").build());
        } else {
            return ResponseEntity.ok().body(ResponseDTO.builder()
                    .mensaje("Algunos animales ya estaban asignados al cuidador")
                    .detalles(animalesRepetidos) // Si deseas incluir los detalles de los animales repetidos
                    .build());
        }
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

    @PostMapping("/actualizarInformacion/{id}")
    public ResponseEntity<?> actualizarInformacion(@PathVariable Long id, @RequestBody UsuarioUpdateDTO usuarioDTO) {
        return ResponseEntity.ok()
                .body(ResponseDTO.builder()
                        .mensaje("Informacion actualizada exitosamente " + cuidadorService.actualizarInformacion(id, usuarioDTO))
                        .build());
    }

}
