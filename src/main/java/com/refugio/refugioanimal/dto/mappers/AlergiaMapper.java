package com.refugio.refugioanimal.dto.mappers;

import com.refugio.refugioanimal.dto.AlergiaDTO;
import com.refugio.refugioanimal.model.Alergia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlergiaMapper {
    Alergia toEntity(AlergiaDTO alergiaDTO);
}
