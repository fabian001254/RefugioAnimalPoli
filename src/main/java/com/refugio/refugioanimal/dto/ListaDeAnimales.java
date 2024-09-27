package com.refugio.refugioanimal.dto;

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
