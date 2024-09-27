package com.refugio.refugioanimal.dto.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.refugio.refugioanimal.dto.UsuarioDTO;
import com.refugio.refugioanimal.dto.UsuarioRegisterDTO;
import com.refugio.refugioanimal.model.Cuidador;
import com.refugio.refugioanimal.model.Usuario;

import java.util.Optional;

public class UsuarioMappers {

    ObjectMapper objectMapper = new ObjectMapper();

    public Usuario usuarioRegisterDTOToUser(UsuarioRegisterDTO usuarioRegisterDTO) {
        return objectMapper.convertValue(usuarioRegisterDTO, Usuario.class);
    }

    public UsuarioDTO usuarioToUsuarioDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .nombreUsuario(usuario.getNombreUsuario())
                .email(usuario.getEmail())
                .rol(usuario.getRol())
                .build();
    }

    public Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO) {
        return objectMapper.convertValue(usuarioDTO, Usuario.class);
    }

    public UsuarioDTO usuarioRegisterDTOToUsuarioDTO(UsuarioRegisterDTO usuarioRegisterDTO) {
        return UsuarioDTO.builder()
                .nombreUsuario(usuarioRegisterDTO.getNombreUsuario())
                .email(usuarioRegisterDTO.getEmail())
                .rol(usuarioRegisterDTO.getRol())
                .build();
    }

    public Cuidador usuarioRegisterDTOToCuidador(UsuarioRegisterDTO usuarioRegisterDTO) {
        return objectMapper.convertValue(usuarioRegisterDTO, Cuidador.class);
    }


}
