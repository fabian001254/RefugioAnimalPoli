package com.refugio.refugioanimal.dto.mappers;

import com.refugio.refugioanimal.dto.MedicamentoDTO;
import com.refugio.refugioanimal.model.Medicamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicamentoMapper {
    Medicamento toEntity(MedicamentoDTO medicamentoDTO);
}
