package com.unicauca.maestria.api.gestionarchivosms.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Data
@Builder
public class Exception {

	private String mensaje;
	private HttpStatus estado;
	private LocalDate marcaTiempo;
	private String descripcionUrl;
}
