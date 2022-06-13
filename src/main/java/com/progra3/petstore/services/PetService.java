package com.progra3.petstore.services;

import java.util.List;

import com.progra3.petstore.entities.Pet;

public interface PetService {
	
	List<Pet> listAll();
	Pet findById(Long id);
	Pet createPet(Pet pet);
	Pet updatePet(Long id, Pet pet);
	void deletePet(Long id);

}
