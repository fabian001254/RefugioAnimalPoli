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
@Table(name = "reporte")
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_generacion", nullable = false)
    private LocalDate fechaGeneracion;

    @Column(name = "tipo_reporte", nullable = false)
    private String tipoReporte;

    @Column(name = "datos_reporte")
    private String datosReporte;

    @ManyToOne
    @JoinColumn(name = "generado_por", nullable = false)
    private Usuario generadoPor;
}
