package com.digimon.digimonapi;

import com.digimon.digimonapi.api.controller.DigimonController;
import com.digimon.digimonapi.api.dto.DigimonInputDTO;
import com.digimon.digimonapi.api.controller.feign.DigimonFeign;
import com.digimon.digimonapi.domain.model.Digimon;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class DigimonApiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DigimonApiApplication.class, args);
    }

    @Autowired
    private DigimonFeign digimonFeign;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DigimonController digimonController;

    @Override
    public void run(String... args) throws Exception {
        List<DigimonInputDTO> digimonList = digimonFeign.getDigimons();

        if(!digimonController.listarDigimons().contains(modelMapper
                .map(digimonList, Digimon.class))) {
            digimonList.stream().forEach(digimonInputDTO -> {
                digimonController.criarDigimon(digimonInputDTO);
            });
        };
    }
}
