package com.digimon.digimonapi.api.controller;

import com.digimon.digimonapi.api.dto.DigimonInputDTO;
import com.digimon.digimonapi.api.dto.DigimonList;
import com.digimon.digimonapi.domain.model.Digimon;
import com.digimon.digimonapi.domain.repository.DigimonRepository;
import com.digimon.digimonapi.domain.service.DigimonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

//    @GetMapping
//    public void importarDigimons () {
//        String uri = "https://digimon-api.vercel.app/api/digimon";
//
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<DigimonList> digimons =  restTemplate.getForEntity(uri, DigimonList.class);
//
//
//
//        System.out.println();
//    }

    @GetMapping
    public List<Digimon> listarDigimons() {
        return digimonRepository.findAll();
    }

    @PostMapping
    public Digimon criarDigimon(DigimonInputDTO digimonInput) {
       Digimon digimon = toEntity(digimonInput);

       return digimon;
    }

}
