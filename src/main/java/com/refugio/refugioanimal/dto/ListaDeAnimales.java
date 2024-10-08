package com.refugio.refugioanimal.dto;

import com.refugio.refugioanimal.model.Animal;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListaDeAnimales {
    List<Long> animales;
}
