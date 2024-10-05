package com.refugio.refugioanimal.dto.usuario;

import com.refugio.refugioanimal.dto.AnimalDTO;
import com.refugio.refugioanimal.model.Animal;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListaAnimales {
    List<AnimalDTO> animalList;
}
