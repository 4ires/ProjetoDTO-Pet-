package com.projetoDTO.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoDTO.dto.PetDTO;
import com.projetoDTO.entties.Pet;
import com.projetoDTO.service.PetService;

import jakarta.validation.Valid;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pet")
public class PetController {
	private final PetService petService;

	@Autowired
	public PetController(PetService petService) {
		this.petService = petService;
	}

	@GetMapping ("/{id}")

	public ResponseEntity<Pet> buscaPetIdControlId (@ PathVariable Long id) {
		Pet pet = petService.buscaPorId(id);
		if (pet != null) {
			return ResponseEntity.ok(pet);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Pet>> buscaTodosPetControl(){
		List<Pet> pet = petService.buscaTodos();
		return ResponseEntity.ok(pet);
	}
	@PostMapping("/")
	public ResponseEntity<PetDTO> salvaPetControl(@RequestBody  @Valid PetDTO petDTO){
		PetDTO salvaPet = petService.salvar(petDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaPet);
	}
	@PutMapping("/{id}")
	public ResponseEntity<PetDTO> alterarPetControl(@PathVariable Long id, @RequestBody @Valid PetDTO petDTO){
		PetDTO alterarPet = petService.atualizar(id, petDTO);
		if(alterarPet != null) {
			return ResponseEntity.ok(alterarPet);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Pet> apagaPetControl(@PathVariable Long id){
		boolean pet = petService.deletar(id);
		if (pet) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
