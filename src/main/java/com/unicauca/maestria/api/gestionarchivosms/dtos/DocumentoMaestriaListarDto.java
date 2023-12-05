package com.unicauca.maestria.api.gestionarchivosms.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentoMaestriaListarDto {

    private Long idDocMaestria;
    private String linkDocumento;
    private Boolean estado;
}
