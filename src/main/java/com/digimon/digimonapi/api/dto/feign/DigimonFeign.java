package com.digimon.digimonapi.api.dto.feign;

import com.digimon.digimonapi.api.dto.DigimonInputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "digimons", url = "https://digimon-api.vercel.app/api/")
public interface DigimonFeign {

    @GetMapping("/digimon")
    List<DigimonInputDTO> getDigimons();

}
