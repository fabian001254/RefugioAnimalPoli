package com.refugio.refugioanimal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacunaDTO {
    private String nombre;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaAdministrada;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate proximaFecha;
}
