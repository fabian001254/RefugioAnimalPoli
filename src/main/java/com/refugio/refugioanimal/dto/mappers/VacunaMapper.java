package com.refugio.refugioanimal.dto.mappers;

import com.refugio.refugioanimal.dto.VacunaDTO;
import com.refugio.refugioanimal.model.Vacuna;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VacunaMapper {
    Vacuna toEntity(VacunaDTO vacunaDTO);
}
