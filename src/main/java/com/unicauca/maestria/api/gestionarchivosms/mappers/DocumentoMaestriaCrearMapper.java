package com.unicauca.maestria.api.gestionarchivosms.mappers;

import com.unicauca.maestria.api.gestionarchivosms.domain.DocumentoMaestria;
import com.unicauca.maestria.api.gestionarchivosms.dtos.DocumentoMaestriaCrearaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentoMaestriaCrearMapper extends GenericMapper<DocumentoMaestriaCrearaDto, DocumentoMaestria> {
}
