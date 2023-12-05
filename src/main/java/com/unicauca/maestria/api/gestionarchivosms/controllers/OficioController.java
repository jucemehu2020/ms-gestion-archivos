package com.unicauca.maestria.api.gestionarchivosms.controllers;

import com.unicauca.maestria.api.gestionarchivosms.dtos.*;
import com.unicauca.maestria.api.gestionarchivosms.services.oficio.OficioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/oficios")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT})
public class OficioController {

    private final OficioService service;

    @PostMapping
    public ResponseEntity<OficioListarDto> crearOficio(@Valid @RequestBody OficioCrearDto oficio, BindingResult result){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(oficio, result));
    }

    @GetMapping
    public ResponseEntity<List<OficioListarDto>> listarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OficioListarDto> buscaarPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<OficioListarDto>> buscarPorEstado(@PathVariable Boolean estado){
        return ResponseEntity.status(HttpStatus.OK).body(service.listarTodosByEstado(estado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OficioListarDto> editarActa(@PathVariable Long id, @Valid @RequestBody OficioCrearDto oficio, BindingResult result){
        return ResponseEntity.status(HttpStatus.OK).body(service.editarOficio(id, oficio, result));
    }

    @GetMapping("/numero/{numeroOficio}/fecha")
    public ResponseEntity<List<OficioListarDto>> buscarPorNumeroActaFechaActa(@PathVariable Long numeroOficio, @RequestParam String fechaOficio) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorNumeroOficioFechOficio(numeroOficio, fechaOficio));
    }

    @GetMapping("/numero/{numeroOficio}")
    public ResponseEntity<List<OficioListarDto>> buscarPorNumeroActa(@PathVariable Long numeroOficio) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorNumeroOficio(numeroOficio));
    }
}
