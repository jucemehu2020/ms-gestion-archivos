package com.unicauca.maestria.api.gestionarchivosms.mappers;

import com.unicauca.maestria.api.gestionarchivosms.domain.Oficio;
import com.unicauca.maestria.api.gestionarchivosms.dtos.OficioListarDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OficioListarMapper extends GenericMapper<OficioListarDto, Oficio>{
}
