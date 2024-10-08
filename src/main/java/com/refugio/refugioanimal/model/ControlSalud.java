package com.refugio.refugioanimal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ControlSalud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    private String nombreProfesional;
    private String especialidad;
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "registro_salud_id", nullable = false)
    private RegistroSalud registroSalud;
}
