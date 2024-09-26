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
@Table(name = "condicion_salud")
public class CondicionSalud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "diagnostico", nullable = false)
    private String diagnostico;

    @Column(name = "tratamiento")
    private String tratamiento;

    @Column(name = "evolucion")
    private String evolucion;

    @ManyToOne
    @JoinColumn(name = "registro_salud_id", nullable = false)
    private RegistroSalud registroSalud;
}
