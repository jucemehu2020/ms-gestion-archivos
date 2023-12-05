package com.unicauca.maestria.api.gestionarchivosms.services.otro;

import com.unicauca.maestria.api.gestionarchivosms.dtos.OtroDocCrearDto;
import com.unicauca.maestria.api.gestionarchivosms.dtos.OtroDocListarDto;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface OtroDocService {

    public OtroDocListarDto crear(OtroDocCrearDto otroDoc, BindingResult result) ;

    public List<OtroDocListarDto> listarTodos() ;

    public OtroDocListarDto buscarPorId(Long id);

    public OtroDocListarDto editarOtroDoc(Long id, OtroDocCrearDto otroDoc, BindingResult result);

    public List<OtroDocListarDto> listarTodosByEstado(Boolean estado);

    public List<OtroDocListarDto> buscarPorNombreDocumento(String nombreDocumento);

}
