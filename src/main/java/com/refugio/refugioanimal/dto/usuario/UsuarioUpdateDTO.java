package com.refugio.refugioanimal.dto.usuario;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioUpdateDTO {
    private String contrasena;
    private String nombreCompleto;
    private String telefono;
}
