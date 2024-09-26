package com.refugio.refugioanimal.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Table(name = "registro_salud")
@Entity
public class RegistroSalud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "procedimientos_veterinarios")
    private String procedimientosVeterinarios;

    @Column(name = "resultados_laboratorio")
    private String resultadosLaboratorio;

    @Column(name = "recomendaciones")
    private String recomendaciones;

    @Column(name = "datos_adicionales")
    private String datosAdicionales;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @OneToMany(mappedBy = "registroSalud", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IndicadorSalud> indicadoresSalud;

    @OneToMany(mappedBy = "registroSalud", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ControlSalud> controlesSalud;

    @OneToMany(mappedBy = "registroSalud", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CondicionSalud> condicionesSalud;

    @OneToMany(mappedBy = "registroSalud", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagen> imagenes;
}
