package com.refugio.refugioanimal.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CondicionSaludDTO {
    private LocalDate fecha;
    private String diagnostico;
    private String evolucion;
    private String tratamiento;
}

