package com.unicauca.maestria.api.gestionarchivosms.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class FieldUniqueException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private Map<String, String> informacionCamposUnicos;

    public FieldUniqueException(Map<String, String> informacionCamposUnicos) {

        this.informacionCamposUnicos = informacionCamposUnicos;
    }


}
