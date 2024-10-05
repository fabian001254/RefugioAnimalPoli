package com.refugio.refugioanimal.services;

import com.refugio.refugioanimal.dto.ListaDeAnimales;
import com.refugio.refugioanimal.dto.ResponseDTO;
import com.refugio.refugioanimal.dto.usuario.UsuarioDTO;
import com.refugio.refugioanimal.dto.usuario.UsuarioLoginDTO;
import com.refugio.refugioanimal.dto.usuario.UsuarioRegisterDTO;
import com.refugio.refugioanimal.dto.mappers.UsuarioMappers;
import com.refugio.refugioanimal.excepciones.NombreDeUsuarioException;
import com.refugio.refugioanimal.model.Usuario;
import com.refugio.refugioanimal.model.enums.Rol;
import com.refugio.refugioanimal.repository.AdministradorRepository;
import com.refugio.refugioanimal.repository.CuidadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private CuidadorRepository cuidadorRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private EncriptarContrasena encriptarContrasena;

    UsuarioMappers usuarioMappers = new UsuarioMappers();


    public ResponseDTO crearUsuario(UsuarioRegisterDTO usuarioDTO) {
        usuarioDTO.setContrasena(encriptarContrasena.encodePassword(usuarioDTO.getContrasena()));
        if(cuidadorRepository.existsByNombreUsuario(usuarioDTO.getNombreUsuario()) || administradorRepository.existsByNombreUsuario(usuarioDTO.getNombreUsuario()))
            throw new NombreDeUsuarioException();

        if(usuarioDTO.getRol().equals(Rol.ADMINISTRADOR))
        {
            administradorRepository.save(usuarioMappers.usuarioRegisterDTOToAdministrador(usuarioDTO));
        }
        else {
            cuidadorRepository.save(usuarioMappers.usuarioRegisterDTOToCuidador(usuarioDTO));
        }

        return ResponseDTO.builder().mensaje("Usuario registrado exitosamente").build();
    }

    public UsuarioDTO iniciarSesion(UsuarioLoginDTO usuarioLoginDTO) {
        Optional<Usuario> usuarioOpt = Optional.ofNullable(cuidadorRepository.findByNombreUsuario(usuarioLoginDTO.getNombreUsuario()));
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if(encriptarContrasena.matchesPassword(usuarioLoginDTO.getContrasena(), usuario.getContrasena()))
            {
                return usuarioMappers.usuarioToUsuarioDTO(usuario);
            }
        }
        return null;
    }
}
