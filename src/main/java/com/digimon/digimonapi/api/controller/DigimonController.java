package com.digimon.digimonapi.api.controller;

import com.digimon.digimonapi.api.dto.DigimonDTO;
import com.digimon.digimonapi.api.dto.DigimonInputDTO;
import com.digimon.digimonapi.domain.model.Digimon;
import com.digimon.digimonapi.domain.repository.DigimonRepository;
import com.digimon.digimonapi.domain.service.DigimonService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("digimons")
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

    @GetMapping
    public List<DigimonDTO> listarDigimons() {

        return digimonService.listarDigimons();
    }

    @GetMapping("name/{name}")
    public List<DigimonDTO> listarDigimonByName(@PathVariable String name) {

        return digimonService.findByName(name);
    }

    @GetMapping("level/{level}")
    public List<DigimonDTO> listarDigimonByLevel(@PathVariable String level) {

        return digimonService.findByLevel(level);
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
