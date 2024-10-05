package com.refugio.refugioanimal.controller;

import com.refugio.refugioanimal.dto.AnimalDTO;
import com.refugio.refugioanimal.dto.ListaDeAnimales;
import com.refugio.refugioanimal.dto.ResponseDTO;
import com.refugio.refugioanimal.dto.UsuarioDetailDTO;
import com.refugio.refugioanimal.dto.usuario.ListaAnimales;
import com.refugio.refugioanimal.dto.usuario.ListaDeCuidadores;
import com.refugio.refugioanimal.dto.usuario.UsuarioDTO;
import com.refugio.refugioanimal.dto.usuario.UsuarioUpdateDTO;
import com.refugio.refugioanimal.services.AnimalService;
import com.refugio.refugioanimal.services.CuidadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/actualizarInformacion/{id}")
    public ResponseEntity<?> actualizarInformacion(@PathVariable Long id, @RequestBody UsuarioUpdateDTO usuarioDTO) {
        return ResponseEntity.ok()
                .body(ResponseDTO.builder()
                        .mensaje("Informacion actualizada exitosamente " + cuidadorService.actualizarInformacion(id, usuarioDTO))
                        .build());
    }

    @GetMapping("/obtenerInformacion/{id}")
    public ResponseEntity<?> obtenerInformacion(@PathVariable Long id) {
        UsuarioDTO usuarioDTO = cuidadorService.obtenerInformacion(id);
        if (usuarioDTO != null) {
            return ResponseEntity.ok().body(ResponseDTO.builder().mensaje("Informacion obtenida exitosamente").usuario(usuarioDTO).build());
        } else {
            return ResponseEntity.ok().body(ResponseDTO.builder().mensaje("Cuidador no encontrado").build());
        }
    }

    @GetMapping("{id}/animales")
    public ResponseEntity<?> obtenerAnimales(@PathVariable Long id) {
        ListaAnimales listaDeAnimales = cuidadorService.obtenerListaDeAnimales(id);
        if(listaDeAnimales != null) {
            return ResponseEntity.ok()
                    .body(ResponseDTO.builder()
                                    .listaDeAnimales(listaDeAnimales).build());
        }
        return ResponseEntity.notFound().build();
    }

}
