package com.refugio.refugioanimal.services;

import com.refugio.refugioanimal.dto.usuario.UsuarioDTO;
import com.refugio.refugioanimal.dto.usuario.UsuarioUpdateDTO;
import com.refugio.refugioanimal.dto.mappers.UsuarioMappers;
import com.refugio.refugioanimal.model.Cuidador;
import com.refugio.refugioanimal.repository.CuidadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CuidadorService {

    @Autowired
    private CuidadorRepository cuidadorRepository;

    UsuarioMappers usuarioMappers = new UsuarioMappers();

    public UsuarioDTO actualizarInformacion(Long id,UsuarioUpdateDTO usuarioDTO)
    {
        Optional<Cuidador> usuario = cuidadorRepository.findById(id);

        if(usuario.isPresent())
        {
            Cuidador cuidador = usuario.get();
            cuidador.setTelefono(usuarioDTO.getTelefono() != null ? usuarioDTO.getTelefono() : cuidador.getTelefono());
            cuidador.setContrasena(usuarioDTO.getContrasena() != null ? usuarioDTO.getContrasena() : cuidador.getContrasena());
            cuidador.setNombreCompleto(usuarioDTO.getNombreCompleto() != null ? usuarioDTO.getNombreCompleto() : cuidador.getNombreCompleto());
            cuidadorRepository.save(cuidador);
            return usuarioMappers.usuarioToUsuarioDTO(cuidador);
        }

        return null;
    }

    public UsuarioDTO obtenerInformacion(Long id)
    {
        Optional<Cuidador> usuario = cuidadorRepository.findById(id);

        if(usuario.isPresent())
        {
            Cuidador cuidador = usuario.get();
            return usuarioMappers.usuarioToUsuarioDTO(cuidador);
        }

        return null;
    }

}
