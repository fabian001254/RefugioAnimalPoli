package com.refugio.refugioanimal.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Cuidador extends Usuario {
    @ManyToMany
    @JoinTable(
            name = "cuidador_animal",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "animal_id", referencedColumnName = "id")
    )
    private List<Animal> animalesACargo;
}

