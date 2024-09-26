package com.refugio.refugioanimal.services;

import com.refugio.refugioanimal.dto.UsuarioDTO;
import com.refugio.refugioanimal.dto.UsuarioLoginDTO;
import com.refugio.refugioanimal.dto.UsuarioRegisterDTO;
import com.refugio.refugioanimal.dto.mappers.UsuarioMappers;
import com.refugio.refugioanimal.model.Usuario;
import com.refugio.refugioanimal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EncriptarContrasena encriptarContrasena;

    UsuarioMappers usuarioMappers = new UsuarioMappers();

    public UsuarioDTO crearUsuario(UsuarioRegisterDTO usuarioDTO) {
        usuarioDTO.setContrasena(encriptarContrasena.encodePassword(usuarioDTO.getContrasena()));
        userRepository.save(usuarioMappers.usuarioRegisterDTOToUser(usuarioDTO));
        return usuarioMappers.usuarioRegisterDTOToUsuarioDTO(usuarioDTO);
    }

    public Optional<Usuario> iniciarSesion(UsuarioLoginDTO usuarioLoginDTO) {
        Optional<Usuario> usuarioOpt = Optional.ofNullable(userRepository.findByNombreUsuario(usuarioLoginDTO.getUsername()));
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (encriptarContrasena.matchesPassword(usuarioLoginDTO.getContrasena(), usuario.getContrasena())) {
                return usuarioOpt;
            }
        }
        return Optional.empty();
    }

    public Optional<UsuarioDTO> obtenerUsuarioPorId(Long id) {
        return Optional.ofNullable(usuarioMappers.usuarioToUsuarioDTO(userRepository.findById(id)));
    }



}
