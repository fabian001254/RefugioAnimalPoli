package com.refugio.refugioanimal.dto;

import com.refugio.refugioanimal.dto.usuario.CuidadorDTO;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListaCuidadores {
    List<CuidadorDTO> listaCuidadores;

}
