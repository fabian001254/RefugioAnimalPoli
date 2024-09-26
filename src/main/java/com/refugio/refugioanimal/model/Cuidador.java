package com.refugio.refugioanimal.model;

import com.refugio.refugioanimal.model.enums.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cuidador extends Usuario {
    @ManyToMany
    @JoinTable(
            name = "cuidador_animal",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "animal_id", referencedColumnName = "id")
    )
    private List<Animal> animalesACargo;
}
