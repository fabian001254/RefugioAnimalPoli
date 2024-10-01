package com.refugio.refugioanimal.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImagenDTO {
    private String url;
    private String descripcion;
}
