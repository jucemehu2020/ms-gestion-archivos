package com.unicauca.maestria.api.gestionarchivosms.mappers;

import com.unicauca.maestria.api.gestionarchivosms.domain.OtroDoc;
import com.unicauca.maestria.api.gestionarchivosms.dtos.OtroDocListarDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OtroDocListarMapper extends GenericMapper<OtroDocListarDto, OtroDoc>{
}
