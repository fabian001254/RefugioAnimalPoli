package com.refugio.refugioanimal.dto;
import jakarta.persistence.Column;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlergiaDTO {
    private String alergeno;
    private String severidad;
    private String notas;
}