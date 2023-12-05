package com.unicauca.maestria.api.gestionarchivosms.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;

@Setter
@Getter
public class FieldErrorException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private BindingResult result;

    public FieldErrorException(BindingResult result) {
        this.result = result;
    }


}
