package com.digimon.digimonapi.domain.repository;

import com.digimon.digimonapi.domain.model.Digimon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DigimonRepository extends JpaRepository<Digimon, Long> {

    Digimon findByName(String name);

    List<Digimon> findByNameContaining(String name);

    List<Digimon> findByLevelContaining(String level);

}
