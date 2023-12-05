package com.unicauca.maestria.api.gestionarchivosms.dtos;

import com.unicauca.maestria.api.gestionarchivosms.domain.DocumentoMaestria;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActaListarDto {

    private Long idActa;
    private DocumentoMaestria idDocMaestria;
    private Long numeroActa;
    private Date fechaActa;
}
