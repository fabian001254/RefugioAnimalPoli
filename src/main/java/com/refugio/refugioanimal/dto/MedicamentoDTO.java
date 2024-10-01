package com.refugio.refugioanimal.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicamentoDTO {
    private String nombre;
    private String dosis;
}
