package com.digimon.digimonapi.api.controller;

import com.digimon.digimonapi.api.dto.DigimonDTO;
import com.digimon.digimonapi.api.dto.DigimonInputDTO;
import com.digimon.digimonapi.domain.exception.NoFoundException;
import com.digimon.digimonapi.domain.model.Digimon;
import com.digimon.digimonapi.domain.repository.DigimonRepository;
import com.digimon.digimonapi.domain.service.DigimonService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/digimons")
public class DigimonController {

    @Autowired
    private DigimonService digimonService;

    @Autowired
    private DigimonRepository digimonRepository;

    @Autowired
    private ModelMapper modelMapper;

    private Digimon toEntity(DigimonInputDTO digimonInput) {
        return modelMapper.map(digimonInput, Digimon.class);
    }

    private DigimonDTO toMap(Digimon digimon) {
        return modelMapper.map(digimon, DigimonDTO.class);
    }

    private List<DigimonDTO> toMapList(List<Digimon> digimons) {
        return digimons.stream().map(digimon -> toMap(digimon)).collect(Collectors.toList());
    }

    @GetMapping
    public List<DigimonDTO> listarDigimons() {

        return toMapList(digimonRepository.findAll());
    }

    @GetMapping("{name}")
    public DigimonDTO listarDigimonByName(@PathVariable String name) {

        return toMap(digimonRepository.findByName(name));
    }

    @GetMapping("{level}")
    public List<DigimonDTO> listarDigimonByLevel(@PathVariable String level) {

        return toMapList(digimonRepository.findByLevel(level));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DigimonDTO criarDigimon(DigimonInputDTO digimonInputDTO) {
        Digimon digimon = toEntity(digimonInputDTO);

        return digimonService.save(digimon);
    }

    @PutMapping("{id}")
    public ResponseEntity<DigimonDTO> atualizarDigimon(@Valid @PathVariable Long id, @RequestBody Digimon digimon) {

        return digimonService.atualizar(id, digimon);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarDigimon(@PathVariable Long id) {

        return digimonService.delete(id);
    }

}
