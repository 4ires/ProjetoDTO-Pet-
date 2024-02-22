package com.projetoDTO.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoDTO.entties.Pet;

public interface PetRepository extends JpaRepository<Pet, Long>{

}
