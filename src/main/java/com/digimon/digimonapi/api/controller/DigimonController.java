package com.digimon.digimonapi.api.controller;

import com.digimon.digimonapi.domain.repository.DigimonRepository;
import com.digimon.digimonapi.domain.service.DigimonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/digimons")
public class DigimonController {

    @Autowired
    private DigimonService digimonService;

    @Autowired
    private DigimonRepository digimonRepository;

    @Autowired
    private ModelMapper modelMapper;

}
