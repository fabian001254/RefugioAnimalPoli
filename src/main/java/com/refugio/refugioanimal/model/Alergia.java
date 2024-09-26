package com.refugio.refugioanimal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "alergia")
public class Alergia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "alergeno", nullable = false)
    private String alergeno;

    @Column(name = "severidad")
    private String severidad;

    @Column(name = "notas")
    private String notas;

    @ManyToOne
    @JoinColumn(name = "indicador_salud_id", nullable = false)
    private IndicadorSalud indicadorSalud;
}
