package com.refugio.refugioanimal.model;

import com.refugio.refugioanimal.model.enums.Habitat;
import com.refugio.refugioanimal.model.enums.TipoDeComida;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String especie;
    Habitat habitat;
    String nombre;
    TipoDeComida tipoDeComida;

    @ManyToMany(mappedBy = "animalesACargo")
    private List<Cuidador> cuidadores;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagen> fotos;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegistroSalud> registrosSalud = new ArrayList<>();
}
