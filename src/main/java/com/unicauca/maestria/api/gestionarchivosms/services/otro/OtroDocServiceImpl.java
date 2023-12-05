package com.unicauca.maestria.api.gestionarchivosms.services.otro;

import com.unicauca.maestria.api.gestionarchivosms.common.util.FilesUtilities;
import com.unicauca.maestria.api.gestionarchivosms.domain.OtroDoc;
import com.unicauca.maestria.api.gestionarchivosms.dtos.OtroDocCrearDto;
import com.unicauca.maestria.api.gestionarchivosms.dtos.OtroDocListarDto;
import com.unicauca.maestria.api.gestionarchivosms.exceptions.FieldErrorException;
import com.unicauca.maestria.api.gestionarchivosms.exceptions.ResourceNotFoundException;
import com.unicauca.maestria.api.gestionarchivosms.mappers.OtroDocCrearMapper;
import com.unicauca.maestria.api.gestionarchivosms.mappers.OtroDocListarMapper;
import com.unicauca.maestria.api.gestionarchivosms.respositories.OtroDocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OtroDocServiceImpl implements OtroDocService{

    private final OtroDocRepository otroDocRepository;
    private final OtroDocCrearMapper otroDocCrearMapper;
    private final OtroDocListarMapper otroDocListarMapper;

    @Override
    public OtroDocListarDto crear(OtroDocCrearDto otroDoc, BindingResult result) {
        if (result.hasErrors()) {
            throw new FieldErrorException(result);
        }

        otroDoc.getIdDocMaestria().setEstado(true);
        otroDoc.getIdDocMaestria().setLinkDocumento(FilesUtilities.guardarArchivo(otroDoc.getIdDocMaestria().getLinkDocumento()));
        OtroDoc otroDocDb = otroDocRepository.save(otroDocCrearMapper.toEntity(otroDoc));

        return otroDocListarMapper.toDto(otroDocDb);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OtroDocListarDto> listarTodos() {
        return otroDocListarMapper.toDtoList(this.otroDocRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public OtroDocListarDto buscarPorId(Long id) {
        return this.otroDocRepository.findById(id).map(otroDocListarMapper::toDto).orElseThrow(() -> new ResourceNotFoundException("OtroDoc con id: " + id + " No encontrada"));
    }

    @Override
    public OtroDocListarDto editarOtroDoc(Long id, OtroDocCrearDto otroDocDto, BindingResult result) {
        OtroDoc otroDocTmp = otroDocRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("OtroDoc con id: " + id + " No encontrada"));
        OtroDoc responseOtroDoc = null;
        if (otroDocTmp != null){
            if (otroDocDto.getIdDocMaestria().getLinkDocumento().compareTo(otroDocTmp.getIdDocMaestria().getLinkDocumento()) != 0){
                otroDocDto.getIdDocMaestria().setLinkDocumento(FilesUtilities.guardarArchivo(otroDocDto.getIdDocMaestria().getLinkDocumento()));
                FilesUtilities.deleteFileExample(otroDocTmp.getIdDocMaestria().getLinkDocumento());
            }
            updateOtroDocValues(otroDocTmp, otroDocDto);
            responseOtroDoc = otroDocRepository.save(otroDocTmp);
        }
        return otroDocListarMapper.toDto(responseOtroDoc);
    }

    private void updateOtroDocValues(OtroDoc otroDoc, OtroDocCrearDto otroDocDto){
        otroDoc.setVersionDoc(otroDocDto.getVersionDoc());
        otroDoc.setNombreDocumento(otroDocDto.getNombreDocumento());
        otroDoc.setDescripcionDocumento(otroDocDto.getDescripcionDocumento());
        otroDoc.getIdDocMaestria().setEstado(otroDocDto.getIdDocMaestria().getEstado());
        otroDoc.getIdDocMaestria().setLinkDocumento(otroDocDto.getIdDocMaestria().getLinkDocumento());
    }

    @Override
    public List<OtroDocListarDto> listarTodosByEstado(Boolean estado) {
        return otroDocListarMapper.toDtoList(this.otroDocRepository.findByEstado(estado));
    }

    @Override
    public List<OtroDocListarDto> buscarPorNombreDocumento(String nombreDocumento){
        return otroDocListarMapper.toDtoList(this.otroDocRepository.findByNombreDocumentoContainingIgnoreCase(nombreDocumento));
    }



}
