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
public class RegistroSalud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private String procedimientosVeterinarios;
    private String resultadosLaboratorio;
    private String recomendaciones;
    private String datosAdicionales;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "registro_salud_id")
    private List<IndicadorSalud> indicadoresSalud;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "registro_salud_id")
    private List<ControlSalud> controlesSalud;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "registro_salud_id")
    private List<CondicionSalud> condicionesSalud;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "registro_salud_id")
    private List<Imagen> imagenes;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;
}
