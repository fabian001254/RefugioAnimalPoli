package com.refugio.refugioanimal.dto.mappers;

import com.refugio.refugioanimal.dto.RegistroSaludDTO;
import com.refugio.refugioanimal.model.RegistroSalud;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegistroSaludMapper {

    @Mapping(target = "animal.id", source = "animalId")
    RegistroSalud toEntity(RegistroSaludDTO registroSaludDTO);

    @Mapping(target = "animalId", source = "animal.id")
    RegistroSaludDTO toDto(RegistroSalud registroSalud);
}


