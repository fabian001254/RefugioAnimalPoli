package com.refugio.refugioanimal.dto.usuario;

import com.refugio.refugioanimal.model.enums.Rol;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuidadorDTO {
    private String nombreUsuario;
    private String email;
    private Rol rol;
    private Long id;
}