package com.refugio.refugioanimal.services;

import com.refugio.refugioanimal.dto.RegistroSaludDTO;
import com.refugio.refugioanimal.dto.mappers.RegistroSaludMapper;
import com.refugio.refugioanimal.excepciones.AnimalesNoEncontradoExeption;
import com.refugio.refugioanimal.model.*;
import com.refugio.refugioanimal.repository.AnimalRepository;
import com.refugio.refugioanimal.repository.RegistroSaludRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegistroSaludService {

    private final RegistroSaludRepository registroSaludRepository;
    private final AnimalRepository animalRepository;
    private final RegistroSaludMapper registroSaludMapper;

    @Autowired
    public RegistroSaludService(RegistroSaludRepository registroSaludRepository,
                                AnimalRepository animalRepository,
                                RegistroSaludMapper registroSaludMapper) {
        this.registroSaludRepository = registroSaludRepository;
        this.animalRepository = animalRepository;
        this.registroSaludMapper = registroSaludMapper;
    }

    public void crearRegistroSalud(RegistroSaludDTO registroSaludDTO) {
        // Obtener el animal por ID
        Animal animal = animalRepository.findById(registroSaludDTO.getAnimalId())
                .orElseThrow(() -> new AnimalesNoEncontradoExeption(List.of(registroSaludDTO.getAnimalId())));

        // Convertir el DTO a entidad
        RegistroSalud registroSalud = registroSaludMapper.toEntity(registroSaludDTO);

        // Asociar el registro de salud con el animal
        registroSalud.setAnimal(animal);
        animal.getRegistrosSalud().add(registroSalud);

        // Asociar cada CondicionSalud con el RegistroSalud si no es nulo
        if (registroSalud.getCondicionesSalud() != null) {
            for (CondicionSalud condicionSalud : registroSalud.getCondicionesSalud()) {
                condicionSalud.setRegistroSalud(registroSalud);
            }
        }

        // Asociar cada ControlSalud con el RegistroSalud si no es nulo
        if (registroSalud.getControlesSalud() != null) {
            for (ControlSalud controlSalud : registroSalud.getControlesSalud()) {
                controlSalud.setRegistroSalud(registroSalud);
            }
        }

        // Asociar cada IndicadorSalud con el RegistroSalud y sus entidades anidadas
        if (registroSalud.getIndicadoresSalud() != null) {
            for (IndicadorSalud indicadorSalud : registroSalud.getIndicadoresSalud()) {
                indicadorSalud.setRegistroSalud(registroSalud);

                // Asociar cada Alergia con el IndicadorSalud si no es nulo
                if (indicadorSalud.getAlergias() != null) {
                    for (Alergia alergia : indicadorSalud.getAlergias()) {
                        alergia.setIndicadorSalud(indicadorSalud);
                    }
                }

                // Asociar cada Vacuna con el IndicadorSalud si no es nulo
                if (indicadorSalud.getVacunas() != null) {
                    for (Vacuna vacuna : indicadorSalud.getVacunas()) {
                        vacuna.setIndicadorSalud(indicadorSalud);
                    }
                }

                // Asociar cada Medicamento con el IndicadorSalud si no es nulo
                if (indicadorSalud.getMedicamentos() != null) {
                    for (Medicamento medicamento : indicadorSalud.getMedicamentos()) {
                        medicamento.setIndicadorSalud(indicadorSalud);
                    }
                }
            }
        }

        // Asociar cada Imagen con el RegistroSalud si no es nulo
        if (registroSalud.getImagenes() != null) {
            for (Imagen imagen : registroSalud.getImagenes()) {
                imagen.setRegistroSalud(registroSalud);
            }
        }

        // Guardar el registro de salud
        registroSaludRepository.save(registroSalud);
    }

    public List<RegistroSaludDTO> obtenerRegistrosSaludPorAnimalId(Long animalId) {
        // Obtener el animal por ID
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new AnimalesNoEncontradoExeption(List.of(animalId)));

        // Obtener los registros de salud del animal
        List<RegistroSalud> registrosSalud = animal.getRegistrosSalud();

        // Convertir los registros de salud a DTOs
        List<RegistroSaludDTO> registrosSaludDTO = registrosSalud.stream()
                .map(registroSaludMapper::toDto)
                .toList();

        return registrosSaludDTO;
    }

}
