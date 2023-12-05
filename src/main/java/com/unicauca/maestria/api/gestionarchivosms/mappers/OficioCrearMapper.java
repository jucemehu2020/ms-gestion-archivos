package com.unicauca.maestria.api.gestionarchivosms.mappers;

import com.unicauca.maestria.api.gestionarchivosms.domain.Oficio;
import com.unicauca.maestria.api.gestionarchivosms.dtos.OficioCrearDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring",
        uses = {DocumentoMaestriaCrearMapper.class})
public interface OficioCrearMapper extends GenericMapper<OficioCrearDto, Oficio>{
}
