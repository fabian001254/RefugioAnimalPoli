package com.refugio.refugioanimal.dto.mappers;

import com.refugio.refugioanimal.dto.AnimalDTO;
import com.refugio.refugioanimal.model.Animal;

public class AnimalMapper {
    public Animal animalDTOToAnimal(AnimalDTO animalDTO) {
        return Animal.builder()
                .especie(animalDTO.getEspecie())
                .habitat(animalDTO.getHabitat())
                .nombre(animalDTO.getNombre())
                .tipoDeComida(animalDTO.getTipoDeComida())
                .build();
    }

    public AnimalDTO animalToAnimalDTO(Animal animal)
    {
        return AnimalDTO.builder()
                .nombre(animal.getNombre())
                .especie(animal.getEspecie())
                .habitat(animal.getHabitat())
                .tipoDeComida(animal.getTipoDeComida())
                .build();
    }
}
