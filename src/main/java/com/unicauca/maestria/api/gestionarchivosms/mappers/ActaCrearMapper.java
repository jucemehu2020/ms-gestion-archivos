package com.unicauca.maestria.api.gestionarchivosms.mappers;

import com.unicauca.maestria.api.gestionarchivosms.domain.Acta;
import com.unicauca.maestria.api.gestionarchivosms.dtos.ActaCrearDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring",
        uses = {DocumentoMaestriaCrearMapper.class})
public interface ActaCrearMapper extends GenericMapper<ActaCrearDto, Acta> {
}
