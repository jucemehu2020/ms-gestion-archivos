package com.unicauca.maestria.api.gestionarchivosms.controllers;

import com.unicauca.maestria.api.gestionarchivosms.dtos.OtroDocCrearDto;
import com.unicauca.maestria.api.gestionarchivosms.dtos.OtroDocListarDto;
import com.unicauca.maestria.api.gestionarchivosms.services.otro.OtroDocService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/otros")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT})
public class OtroDocController {

    private final OtroDocService service;

    @PostMapping
    public ResponseEntity<OtroDocListarDto> crearOtroDoc(@Valid @RequestBody OtroDocCrearDto otroDoc, BindingResult result){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(otroDoc, result));
    }

    @GetMapping
    public ResponseEntity<List<OtroDocListarDto>> listarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OtroDocListarDto> buscaarPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<OtroDocListarDto>> buscarPorEstado(@PathVariable Boolean estado){
        return ResponseEntity.status(HttpStatus.OK).body(service.listarTodosByEstado(estado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OtroDocListarDto> editarOtroDoc(@PathVariable Long id, @Valid @RequestBody OtroDocCrearDto otroDoc, BindingResult result){
        return ResponseEntity.status(HttpStatus.OK).body(service.editarOtroDoc(id, otroDoc, result));
    }

    @GetMapping("/nombre/{nombreDocumento}")
    public ResponseEntity<List<OtroDocListarDto>> buscarPorNombreDocumento(@PathVariable String nombreDocumento) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorNombreDocumento(nombreDocumento));
    }
}
