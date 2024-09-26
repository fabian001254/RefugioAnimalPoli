package com.refugio.refugioanimal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "indicador_salud")
public class IndicadorSalud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "peso")
    private BigDecimal peso;

    @Column(name = "otros_indicadores")
    private String otrosIndicadores;

    @ManyToOne
    @JoinColumn(name = "registro_salud_id", nullable = false)
    private RegistroSalud registroSalud;

    @OneToMany(mappedBy = "indicadorSalud", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vacuna> vacunas;

    @OneToMany(mappedBy = "indicadorSalud", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alergia> alergias;

    @OneToMany(mappedBy = "indicadorSalud", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medicamento> medicamentos;
}
