package com.unicauca.maestria.api.gestionarchivosms.services.oficio;

import com.unicauca.maestria.api.gestionarchivosms.common.util.FilesUtilities;
import com.unicauca.maestria.api.gestionarchivosms.domain.Oficio;
import com.unicauca.maestria.api.gestionarchivosms.dtos.OficioCrearDto;
import com.unicauca.maestria.api.gestionarchivosms.dtos.OficioListarDto;
import com.unicauca.maestria.api.gestionarchivosms.exceptions.FieldErrorException;
import com.unicauca.maestria.api.gestionarchivosms.exceptions.ResourceNotFoundException;
import com.unicauca.maestria.api.gestionarchivosms.mappers.OficioCrearMapper;
import com.unicauca.maestria.api.gestionarchivosms.mappers.OficioListarMapper;
import com.unicauca.maestria.api.gestionarchivosms.respositories.OficioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OficioServiceImpl implements OficioService {

    private final OficioRepository oficioRepository;
    private final OficioCrearMapper oficioCrearMapper;
    private final OficioListarMapper oficioListarMapper;

    @Override
    public OficioListarDto crear(OficioCrearDto oficio, BindingResult result) {
        if (result.hasErrors()) {
            throw new FieldErrorException(result);
        }

        oficio.getIdDocMaestria().setEstado(true);
        oficio.getIdDocMaestria().setLinkDocumento(FilesUtilities.guardarArchivo(oficio.getIdDocMaestria().getLinkDocumento()));
        Oficio oficioDb = oficioRepository.save(oficioCrearMapper.toEntity(oficio));

        return oficioListarMapper.toDto(oficioDb);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OficioListarDto> listarTodos() {
        return oficioListarMapper.toDtoList(this.oficioRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public OficioListarDto buscarPorId(Long id) {
        return this.oficioRepository.findById(id).map(oficioListarMapper::toDto).orElseThrow(() -> new ResourceNotFoundException("Oficio con id: " + id + " No encontrada"));
    }

    @Override
    public OficioListarDto editarOficio(Long id, OficioCrearDto oficioDto, BindingResult result) {
        Oficio oficioTmp = oficioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Oficio con id: " + id + " No encontrada"));
        Oficio responseOficio = null;
        if (oficioTmp != null){
            if (oficioDto.getIdDocMaestria().getLinkDocumento().compareTo(oficioTmp.getIdDocMaestria().getLinkDocumento()) != 0){
                oficioDto.getIdDocMaestria().setLinkDocumento(FilesUtilities.guardarArchivo(oficioDto.getIdDocMaestria().getLinkDocumento()));
                FilesUtilities.deleteFileExample(oficioTmp.getIdDocMaestria().getLinkDocumento());
            }
            updateOficioValues(oficioTmp, oficioDto);
            responseOficio = oficioRepository.save(oficioTmp);
        }
        return oficioListarMapper.toDto(responseOficio);
    }

    private void updateOficioValues(Oficio oficio, OficioCrearDto oficioDto){
        oficio.setAsuntoOfi(oficioDto.getAsuntoOfi());
        oficio.setFechaOficio(oficioDto.getFechaOficio());
        oficio.setNumeroOficio(oficioDto.getNumeroOficio());
        oficio.getIdDocMaestria().setEstado(oficioDto.getIdDocMaestria().getEstado());
        oficio.getIdDocMaestria().setLinkDocumento(oficioDto.getIdDocMaestria().getLinkDocumento());
    }

    @Override
    public List<OficioListarDto> listarTodosByEstado(Boolean estado) {
        return oficioListarMapper.toDtoList(this.oficioRepository.findByEstado(estado));
    }

    @Override
    public List<OficioListarDto> buscarPorNumeroOficioFechOficio(Long numeroOficio, String fechaOficio) {
        List<OficioListarDto> response = new ArrayList<>();
        Date fecha = new Date(fechaOficio);
        response.addAll(oficioListarMapper.toDtoList(this.oficioRepository.findByNumeroOficioAndFechaOficio(numeroOficio, fecha)));
        return response;
    }

    @Override
    public List<OficioListarDto> buscarPorNumeroOficio(Long numeroOficio) {
        return oficioListarMapper.toDtoList(this.oficioRepository.findByNumeroOficio(numeroOficio));
    }
}
