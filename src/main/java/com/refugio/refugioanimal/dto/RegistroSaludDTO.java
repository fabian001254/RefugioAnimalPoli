package com.refugio.refugioanimal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistroSaludDTO {
    @NotNull(message = "La fecha es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @NotBlank(message = "Los procedimientos veterinarios son obligatorios")
    @Size(max = 255, message = "Los procedimientos veterinarios no deben exceder los 255 caracteres")
    private String procedimientosVeterinarios;

    private String resultadosLaboratorio;
    private String recomendaciones;
    private String datosAdicionales;

    @NotEmpty(message = "Debe haber al menos un indicador de salud")
    private List<IndicadorSaludDTO> indicadoresSalud;

    // Campos opcionales
    private List<ControlSaludDTO> controlesSalud;
    private List<CondicionSaludDTO> condicionesSalud;
    private List<ImagenDTO> imagenes;

    @NotNull(message = "El ID del animal es obligatorio")
    private Long animalId;
}
