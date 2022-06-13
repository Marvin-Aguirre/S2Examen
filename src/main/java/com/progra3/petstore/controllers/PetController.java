package com.progra3.petstore.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.progra3.petstore.entities.Pet;
import com.progra3.petstore.services.PetService;
@RestController
@RequestMapping("/pet")
public class PetController {
	
	@Autowired
	PetService service;
	
	@GetMapping()
	public List<Pet> findAll(){
		return service.listAll();
	}
	
	@GetMapping(value = "/{id}")
	public Pet findPet(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public Pet createPet(@Valid @RequestBody Pet pet) {
		pet.setId(null);
		return service.createPet(pet);
	}
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Pet updatePet(@PathVariable Long id, @RequestBody Pet pet) {
		return service.updatePet(id, pet);
	}
	
	@DeleteMapping(value = "/{id}")
	public void deletePet(@PathVariable Long id) {
		service.deletePet(id);
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	 public Map<String, String> handleValidationExceptions(
	   MethodArgumentNotValidException ex) {
	     Map<String, String> errors = new HashMap();
	     ex.getBindingResult().getAllErrors().forEach((error) -> {
	         String fieldName = ((FieldError) error).getField();
	         String errorMessage = error.getDefaultMessage();
	         errors.put(fieldName, errorMessage);
	     });
	     return errors;
	 }

}
