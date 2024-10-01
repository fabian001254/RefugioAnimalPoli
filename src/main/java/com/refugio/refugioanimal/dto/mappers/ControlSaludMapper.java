package com.refugio.refugioanimal.dto.mappers;

import com.refugio.refugioanimal.dto.ControlSaludDTO;
import com.refugio.refugioanimal.model.ControlSalud;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ControlSaludMapper {

    ControlSalud toEntity(ControlSaludDTO controlSaludDTO);
}
