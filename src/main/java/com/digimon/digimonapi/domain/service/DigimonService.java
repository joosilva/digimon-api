package com.digimon.digimonapi.domain.service;

import com.digimon.digimonapi.api.dto.DigimonDTO;
import com.digimon.digimonapi.api.dto.DigimonInputDTO;
import com.digimon.digimonapi.domain.exception.AlreadyExistsException;
import com.digimon.digimonapi.domain.exception.NoFoundException;
import com.digimon.digimonapi.domain.model.Digimon;
import com.digimon.digimonapi.domain.repository.DigimonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DigimonService {

    @Autowired
    DigimonRepository digimonRepository;

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

    public List<DigimonDTO> listarDigimons() {

        return toMapList(digimonRepository.findAll());
    }

    public List<DigimonDTO> findByName(String name) {
        List<Digimon> digimons = digimonRepository.findByNameContaining(name);

        if(digimons.isEmpty()) throw new NoFoundException("Não existe nenhum digimon com esse nome.");

        return toMapList(digimons);
    }

    public List<DigimonDTO> findByLevel(String level) {
        List<Digimon> digimons = digimonRepository.findByLevelContaining(level);

        if(digimons.isEmpty()) throw new NoFoundException("Nenhum digimon está nesse nível.");

        return toMapList(digimons);
    }

    public DigimonDTO save(Digimon digimon) {
        Digimon digimonExistente = digimonRepository.findByName(digimon.getName());

        if(digimonExistente != null && !digimon.getId().equals(digimonExistente.getId())) throw new AlreadyExistsException("Este digimon já existe.");

        return toMap(digimonRepository.save(digimon));
    }

    public ResponseEntity<DigimonDTO> atualizar(Long id, Digimon digimon) {
        if(digimonRepository.existsById(id)) {
            digimon.setId(id);

            DigimonDTO digimonDTO = save(digimon);

            return ResponseEntity.ok(digimonDTO);
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> delete(Long id) {
        if(digimonRepository.existsById(id)) {
            digimonRepository.deleteById(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
