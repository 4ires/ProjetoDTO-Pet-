package com.projetoDTO.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoDTO.dto.PetDTO;
import com.projetoDTO.entties.Pet;
import com.projetoDTO.repository.PetRepository;

@Service
public class PetService {

		private final PetRepository petRepository;
		
		@Autowired
		public PetService (PetRepository petRepository){
		this.petRepository = petRepository;
		}
		
		public PetDTO salvar(PetDTO petDTO) {
			Pet pet = new Pet(petDTO.id(), petDTO.nome(),petDTO.nascimento(), petDTO.cuidador());
			Pet salvarPet = petRepository.save(pet);
			return new PetDTO(salvarPet.getId(), salvarPet.getNome(), salvarPet.getNascimento(), salvarPet.getCuidador());	
		}
		
		public PetDTO atualizar(Long id, PetDTO petDTO) {
			Pet existePet = petRepository.findById(id).orElseThrow(() -> new RuntimeException("Registro não encontrado"));
			
			existePet.setNome(petDTO.nome());
			existePet.setNascimento(petDTO.nascimento());
			existePet.setCuidador(petDTO.cuidador());
			
			Pet updatePet = petRepository.save(existePet);
			return new PetDTO(updatePet.getId(), updatePet.getNome(), updatePet.getNascimento(), updatePet.getCuidador());
		}
		
		public boolean deletar(Long id) {
			Optional <Pet> existePet = petRepository.findById(id);
			if (existePet.isPresent()) {
				petRepository.deleteById(id);
				return true;
			}
			return false;
		}
		
		public List<Pet> buscaTodos(){
			return petRepository.findAll();
		}
		
		public Pet buscaPorId(Long id) {
			Optional <Pet> pet = petRepository.findById(id);
			return pet.orElse(null);
		}
}
