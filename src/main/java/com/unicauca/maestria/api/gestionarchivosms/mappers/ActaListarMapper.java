package com.unicauca.maestria.api.gestionarchivosms.mappers;

import com.unicauca.maestria.api.gestionarchivosms.domain.Acta;
import com.unicauca.maestria.api.gestionarchivosms.dtos.ActaListarDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActaListarMapper extends GenericMapper<ActaListarDto, Acta>{
}
