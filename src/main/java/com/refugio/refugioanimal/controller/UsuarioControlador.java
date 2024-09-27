package com.refugio.refugioanimal.controller;

import com.refugio.refugioanimal.dto.ResponseDTO;
import com.refugio.refugioanimal.dto.usuario.UsuarioLoginDTO;
import com.refugio.refugioanimal.dto.usuario.UsuarioRegisterDTO;
import com.refugio.refugioanimal.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
@Validated
public class UsuarioControlador {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody UsuarioRegisterDTO usuario)
    {
        return ResponseEntity.ok().body(usuarioService.crearUsuario(usuario));
    }


    @GetMapping("/login")
    public ResponseEntity<?> iniciarSesion(@RequestBody UsuarioLoginDTO usuario)
    {
        if(usuarioService.iniciarSesion(usuario)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseDTO.builder().error("Credenciales inválidas").build());
    }

}
