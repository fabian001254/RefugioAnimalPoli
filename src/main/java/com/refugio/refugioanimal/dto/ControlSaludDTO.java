package com.refugio.refugioanimal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ControlSaludDTO {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    private String nombreProfesional;
    private String especialidad;
    private String observaciones;
}
