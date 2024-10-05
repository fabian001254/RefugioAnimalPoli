package com.refugio.refugioanimal.services;

import com.refugio.refugioanimal.dto.AnimalDTO;
import com.refugio.refugioanimal.dto.mappers.AnimalMapper;
import com.refugio.refugioanimal.dto.ListaDeAnimales;
import com.refugio.refugioanimal.dto.mappers.AnimalMapper;
import com.refugio.refugioanimal.dto.usuario.ListaAnimales;
import com.refugio.refugioanimal.dto.usuario.UsuarioDTO;
import com.refugio.refugioanimal.dto.usuario.UsuarioUpdateDTO;
import com.refugio.refugioanimal.dto.mappers.UsuarioMappers;
import com.refugio.refugioanimal.model.Cuidador;
import com.refugio.refugioanimal.model.RegistroSalud;
import com.refugio.refugioanimal.repository.CuidadorRepository;
import static java.util.stream.Collectors.toList;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuidadorService {

    @Autowired
    private CuidadorRepository cuidadorRepository;

    AnimalMapper animalMapper = new AnimalMapper();

    AnimalMapper animalMapper = new AnimalMapper();

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


    public List<AnimalDTO> obtenerAnimalesACargo(Long id)
    {
        Optional<Cuidador> usuario = cuidadorRepository.findById(id);

        if(usuario.isPresent())
        {
            Cuidador cuidador = usuario.get();
            return cuidador.getAnimalesACargo().stream().map(animalMapper::animalToAnimalDTO).toList();
        }

        return null;
    }



    public ListaAnimales obtenerListaDeAnimales(Long id) {
        return cuidadorRepository.findById(id)
                .map(cuidador -> {
                    ListaAnimales listaAnimales = new ListaAnimales();
                    listaAnimales.setAnimalList(
                            cuidador.getAnimalesACargo().stream().map(animalMapper::animalToAnimalDTOList).toList()
                    );
                    return listaAnimales;
                })
                .orElseThrow(() -> new EntityNotFoundException("Cuidador con ID " + id + " no encontrado"));
    }


}
