package com.digimon.digimonapi.domain.service;

import com.digimon.digimonapi.api.dto.DigimonDTO;
import com.digimon.digimonapi.api.dto.DigimonInputDTO;
import com.digimon.digimonapi.domain.exception.AlreadyExistsException;
import com.digimon.digimonapi.domain.model.Digimon;
import com.digimon.digimonapi.domain.repository.DigimonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DigimonService {

    @Autowired
    DigimonRepository digimonRepository;

    @Autowired
    private ModelMapper modelMapper;

    private DigimonDTO toMap(Digimon digimon) {
        return modelMapper.map(digimon, DigimonDTO.class);
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
