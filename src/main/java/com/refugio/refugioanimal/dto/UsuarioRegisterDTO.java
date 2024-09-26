package com.refugio.refugioanimal.dto;

import com.refugio.refugioanimal.model.enums.Rol;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioRegisterDTO {
    private String nombreUsuario;
    private String contrasena;
    private String nombreCompleto;
    @Email
    private String email;
    private String telefono;
    private Rol rol;
}
