package com.unicauca.maestria.api.gestionarchivosms.services.acta;

import com.unicauca.maestria.api.gestionarchivosms.common.util.FilesUtilities;
import com.unicauca.maestria.api.gestionarchivosms.domain.Acta;
import com.unicauca.maestria.api.gestionarchivosms.dtos.ActaCrearDto;
import com.unicauca.maestria.api.gestionarchivosms.dtos.ActaListarDto;
import com.unicauca.maestria.api.gestionarchivosms.exceptions.FieldErrorException;
import com.unicauca.maestria.api.gestionarchivosms.exceptions.ResourceNotFoundException;
import com.unicauca.maestria.api.gestionarchivosms.mappers.ActaCrearMapper;
import com.unicauca.maestria.api.gestionarchivosms.mappers.ActaListarMapper;
import com.unicauca.maestria.api.gestionarchivosms.respositories.ActaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ActaServiceImpl implements ActaService {

    private final ActaRepository actaRepository;
    private final ActaCrearMapper actaCrearMapper;
    private final ActaListarMapper actaListarMapper;

    @Override
    public ActaListarDto crear(ActaCrearDto acta, BindingResult result) {
        if (result.hasErrors()) {
            throw new FieldErrorException(result);
        }
        acta.getIdDocMaestria().setEstado(true);
        acta.getIdDocMaestria().setLinkDocumento(FilesUtilities.guardarArchivo(acta.getIdDocMaestria().getLinkDocumento()));
        Acta actaDb = actaRepository.save(actaCrearMapper.toEntity(acta));
        return actaListarMapper.toDto(actaDb);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActaListarDto> listarTodos() {
        return actaListarMapper.toDtoList(this.actaRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ActaListarDto buscarPorId(Long id) {
        return this.actaRepository.findById(id).map(actaListarMapper::toDto).orElseThrow(() -> new ResourceNotFoundException("Acta con id: " + id + " No encontrada"));
    }

    @Override
    public ActaListarDto editarActa(Long id, ActaCrearDto actaDto, BindingResult result) {
        Acta actaTmp = actaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Acta con id: " + id + " No encontrada"));
        Acta responseActa = null;
        if (actaTmp != null){
            if (actaDto.getIdDocMaestria().getLinkDocumento().compareTo(actaTmp.getIdDocMaestria().getLinkDocumento()) != 0){
                actaDto.getIdDocMaestria().setLinkDocumento(FilesUtilities.guardarArchivo(actaDto.getIdDocMaestria().getLinkDocumento()));
                FilesUtilities.deleteFileExample(actaTmp.getIdDocMaestria().getLinkDocumento());
            }
            updateActaValues(actaTmp, actaDto);
            responseActa = actaRepository.save(actaTmp);
        }
        return actaListarMapper.toDto(responseActa);
    }

    private void updateActaValues(Acta acta, ActaCrearDto actaDto){
        acta.setFechaActa(actaDto.getFechaActa());
        acta.setNumeroActa(actaDto.getNumeroActa());
        acta.getIdDocMaestria().setEstado(actaDto.getIdDocMaestria().getEstado());
        acta.getIdDocMaestria().setLinkDocumento(actaDto.getIdDocMaestria().getLinkDocumento());
    }

    public List<ActaListarDto> listarTodosByEstado(Boolean estado){
        return actaListarMapper.toDtoList(this.actaRepository.findByEstado(estado));
    }

    @Override
    public List<ActaListarDto> buscarPorNumeroActaFechaActa(Long numeroActa, String fechaActa){
        List<ActaListarDto> response = new ArrayList<>();
        Date fecha = new Date(fechaActa);
        response.addAll(this.actaListarMapper.toDtoList(this.actaRepository.findByNumeroActaAndFechaActa(numeroActa, fecha)));
        return response;
    }

    @Override
    public List<ActaListarDto> buscarPorNumeroActa(Long numeroActa){
        return this.actaListarMapper.toDtoList(this.actaRepository.findByNumeroActa(numeroActa));
    }
}
