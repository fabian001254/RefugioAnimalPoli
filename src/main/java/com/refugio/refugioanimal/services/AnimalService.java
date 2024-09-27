package com.refugio.refugioanimal.services;

import com.refugio.refugioanimal.dto.AnimalDTO;
import com.refugio.refugioanimal.dto.ListaDeAnimales;
import com.refugio.refugioanimal.dto.mappers.AnimalMapper;
import com.refugio.refugioanimal.dto.usuario.ListaDeCuidadores;
import com.refugio.refugioanimal.dto.usuario.UsuarioDTO;
import com.refugio.refugioanimal.dto.mappers.UsuarioMappers;
import com.refugio.refugioanimal.excepciones.AnimalesNoEncontradoExeption;
import com.refugio.refugioanimal.excepciones.UsuariosNoEncontradoExeption;
import com.refugio.refugioanimal.model.Animal;
import com.refugio.refugioanimal.model.Cuidador;
import com.refugio.refugioanimal.repository.AnimalRepository;
import com.refugio.refugioanimal.repository.CuidadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private CuidadorRepository cuidadorRepository;

    AnimalMapper animalMapper = new AnimalMapper();

    UsuarioMappers usuarioMappers = new UsuarioMappers();
    List<UsuarioDTO> usuariosRepetidos = new ArrayList<>();


    public void asignarCuidador(Long idAnimal, ListaDeCuidadores listaDeCuidadores) {
        List<Long> cuidadores = listaDeCuidadores.getCuidadores();
        List<Cuidador> cuidadorList = cuidadorRepository.findAllById(cuidadores);

        if (cuidadorList.size() != cuidadores.size()) {
            List<Long> foundIds = cuidadorList.stream().map(Cuidador::getId).toList();
            List<Long> missingIds = cuidadores.stream().filter(id -> !foundIds.contains(id)).toList();
            throw new UsuariosNoEncontradoExeption(missingIds);
        }

        animalRepository.findById(idAnimal).ifPresent(animal -> {
            cuidadorList.forEach(cuidador -> {
                if(!animal.getCuidadores().contains(cuidador)) {
                    animal.getCuidadores().add(cuidador);
                }
                else
                {
                    usuariosRepetidos.add(usuarioMappers.usuarioToUsuarioDTO(cuidador));
                }
            });
            animalRepository.save(animal);
        });
    }

    public void asignarAnimales(Long idCuidador, ListaDeAnimales listaAnimales) {
        List<Long> animales = listaAnimales.getAnimales();
        List<Animal> animalList = animalRepository.findAllById(animales);
        List<UsuarioDTO> usuariosRepetidos = new ArrayList<>();

        if (animalList.size() != animales.size()) {
            List<Long> foundIds = animalList.stream().map(Animal::getId).toList();
            List<Long> missingIds = animales.stream().filter(id -> !foundIds.contains(id)).toList();
            throw new AnimalesNoEncontradoExeption(missingIds);
        }

        cuidadorRepository.findById(idCuidador).ifPresent(cuidador -> {
            animalList.forEach(animal -> {
                if (!animal.getCuidadores().contains(cuidador)) {
                    animal.getCuidadores().add(cuidador);
                } else {
                    usuariosRepetidos.add(usuarioMappers.usuarioToUsuarioDTO(cuidador));
                }
            });
            cuidadorRepository.save(cuidador);
        });
    }

    public void crearAnimal(AnimalDTO animalDTO)
    {
        animalRepository.save(animalMapper.animalToAnimalDTO(animalDTO));
    }

}
