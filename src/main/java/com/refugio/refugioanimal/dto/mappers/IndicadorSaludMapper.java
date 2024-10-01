package com.refugio.refugioanimal.dto.mappers;

import com.refugio.refugioanimal.dto.IndicadorSaludDTO;
import com.refugio.refugioanimal.model.IndicadorSalud;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IndicadorSaludMapper {
    IndicadorSalud toEntity(IndicadorSaludDTO indicadorSaludDTO);
}
