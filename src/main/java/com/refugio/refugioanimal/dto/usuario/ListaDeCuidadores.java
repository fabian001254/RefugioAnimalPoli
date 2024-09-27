package com.refugio.refugioanimal.dto.usuario;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListaDeCuidadores {
    List<Long> cuidadores;
}
