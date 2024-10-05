package com.refugio.refugioanimal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.refugio.refugioanimal.dto.usuario.UsuarioDTO;
import com.refugio.refugioanimal.dto.usuario.ListaAnimales;
import com.refugio.refugioanimal.dto.usuario.UsuarioDTO;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {
    String mensaje;
    String error;
    List<?> detalles;
    UsuarioDTO usuario;
    String datos;
    RegistroSaludDTO registroSalud;
    ListaAnimales listaDeAnimales;
    ListaCuidadores listaDeCuidadores;
}
