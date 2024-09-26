package com.refugio.refugioanimal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vacuna")
public class Vacuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_administrada")
    private LocalDate fechaAdministrada;

    @Temporal(TemporalType.DATE)
    @Column(name = "proxima_fecha")
    private LocalDate proximaFecha;

    @ManyToOne
    @JoinColumn(name = "indicador_salud_id", nullable = false)
    private IndicadorSalud indicadorSalud;
}
