package com.sistema_controle_abastecimento.api.controller;


import com.sistema_controle_abastecimento.application.dto.AbastecimentoDTO;
import com.sistema_controle_abastecimento.application.dto.AbastecimentoRequestDTO;
import com.sistema_controle_abastecimento.application.service.AbastecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/abastecimentos")
public class AbastecimentoController {

    private final AbastecimentoService abastecimentoService;

    @Autowired
    public AbastecimentoController(AbastecimentoService abastecimentoService){
        this.abastecimentoService = abastecimentoService;
    }

    @GetMapping
    public ResponseEntity<Page<AbastecimentoDTO>> getAll(@RequestParam(required = false) String placa, Pageable pageable){
        return ResponseEntity.ok(abastecimentoService.getAll(pageable, placa));
    }

    @PostMapping
    public ResponseEntity<AbastecimentoDTO> add(@Valid @RequestBody AbastecimentoRequestDTO abastecimentoRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(abastecimentoService.add(abastecimentoRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        abastecimentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}