package com.refugio.refugioanimal.dto;

import com.refugio.refugioanimal.model.enums.Rol;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
    private String nombreUsuario;
    private String email;
    private Rol rol;
}
