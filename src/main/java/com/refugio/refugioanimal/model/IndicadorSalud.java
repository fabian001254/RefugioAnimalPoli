package com.refugio.refugioanimal.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IndicadorSalud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private Double peso;
    private String otrosIndicadores;

    @OneToMany(mappedBy = "indicadorSalud", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alergia> alergias;

    @OneToMany(mappedBy = "indicadorSalud", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vacuna> vacunas;

    @OneToMany(mappedBy = "indicadorSalud", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medicamento> medicamentos;

    @ManyToOne
    @JoinColumn(name = "registro_salud_id", nullable = false)
    private RegistroSalud registroSalud;
}
