package com.CulinariaRestrita.Sg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CulinariaRestrita.Sg.dto.IngredientsDto;
import com.CulinariaRestrita.Sg.model.Ingredients;
import com.CulinariaRestrita.Sg.repositories.IngredientsRepository;

@Service
public class IngredientsService {

	@Autowired
	private IngredientsRepository ingredientsRepository;
	
	
	
	public List<IngredientsDto>getAll(){
		 List<IngredientsDto> ingreList = ingredientsRepository.findAll().stream().map(IngredientsDto::new).toList();
		  return ingreList;
	}
	
	
	public List<IngredientsDto>getAllByRecipe(Long id){
		 List<IngredientsDto> ingreList = ingredientsRepository.findByRecipeId(id).stream().map(IngredientsDto::new).toList();
		  return ingreList;
	}
	
	public Ingredients saveIngredient(Ingredients ingredient) {
		 return ingredientsRepository.save(ingredient);
	  }
	
}
