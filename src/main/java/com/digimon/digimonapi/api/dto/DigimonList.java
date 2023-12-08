package com.digimon.digimonapi.api.dto;

import com.digimon.digimonapi.domain.model.Digimon;
import lombok.Data;

import java.util.ArrayList;

@Data
public class DigimonList {

    private ArrayList<Digimon> digimons;

}
