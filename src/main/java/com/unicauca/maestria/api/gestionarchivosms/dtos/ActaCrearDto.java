package com.unicauca.maestria.api.gestionarchivosms.dtos;

import com.unicauca.maestria.api.gestionarchivosms.domain.DocumentoMaestria;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActaCrearDto {

    @NotNull
    @Valid
    private DocumentoMaestria idDocMaestria;

    @NotNull
    @Positive
    private Long numeroActa;

    @NotNull
    @PastOrPresent
    private Date fechaActa;
}
