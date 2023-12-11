package com.digimon.digimonapi;

import com.digimon.digimonapi.api.controller.DigimonController;
import com.digimon.digimonapi.api.dto.DigimonInputDTO;
import com.digimon.digimonapi.api.dto.feign.DigimonFeign;
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
    private DigimonController digimonController;

    @Override
    public void run(String... args) throws Exception {
        List<DigimonInputDTO> digimonList = digimonFeign.getDigimons();

        digimonList.stream().forEach(digimonInputDTO -> {
            digimonController.criarDigimon(digimonInputDTO);
        });
    }
}
