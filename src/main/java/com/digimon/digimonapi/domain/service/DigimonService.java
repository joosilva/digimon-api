package com.digimon.digimonapi.domain.service;

import com.digimon.digimonapi.domain.repository.DigimonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DigimonService {

    @Autowired
    DigimonRepository digimonRepository;

}
