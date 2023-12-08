package com.digimon.digimonapi.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DigimonInputDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String img;

    @NotBlank
    private String level;

}
