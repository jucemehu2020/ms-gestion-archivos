package com.unicauca.maestria.api.gestionarchivosms.dtos;

import com.unicauca.maestria.api.gestionarchivosms.domain.DocumentoMaestria;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtroDocListarDto {

    private Long idOtroDoc;
    private DocumentoMaestria idDocMaestria;
    private String nombreDocumento;
    private Long versionDoc;
    private String descripcionDocumento;
}
