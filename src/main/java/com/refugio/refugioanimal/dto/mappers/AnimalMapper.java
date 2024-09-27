package com.refugio.refugioanimal.dto.mappers;

import com.refugio.refugioanimal.dto.AnimalDTO;
import com.refugio.refugioanimal.model.Animal;

public class AnimalMapper {
    public Animal animalToAnimalDTO(AnimalDTO animalDTO) {
        return Animal.builder()
                .especie(animalDTO.getEspecie())
                .habitat(animalDTO.getHabitat())
                .nombre(animalDTO.getNombre())
                .tipoDeComida(animalDTO.getTipoDeComida())
                .build();
    }
}
