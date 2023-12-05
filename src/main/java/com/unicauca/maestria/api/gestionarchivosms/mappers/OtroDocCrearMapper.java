package com.unicauca.maestria.api.gestionarchivosms.mappers;

import com.unicauca.maestria.api.gestionarchivosms.domain.OtroDoc;
import com.unicauca.maestria.api.gestionarchivosms.dtos.OtroDocCrearDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring",
        uses = {DocumentoMaestriaCrearMapper.class})
public interface OtroDocCrearMapper extends GenericMapper<OtroDocCrearDto, OtroDoc>{
}
