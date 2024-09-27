package com.refugio.refugioanimal.dto;

import com.refugio.refugioanimal.model.enums.Habitat;
import com.refugio.refugioanimal.model.enums.TipoDeComida;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimalDTO {
    String especie;
    Habitat habitat;
    String nombre;
    TipoDeComida tipoDeComida;
}
