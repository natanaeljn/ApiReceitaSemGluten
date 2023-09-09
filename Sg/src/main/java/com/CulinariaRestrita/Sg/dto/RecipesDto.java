package com.CulinariaRestrita.Sg.dto;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.CulinariaRestrita.Sg.model.Recipes;



public record RecipesDto(Long id , String nameRecipe , String tipo , String preparation , String image ) {
	

	public RecipesDto(Recipes recipes) {
		this(recipes.getId() , recipes.getNameRecipe(), recipes.getTipo() , recipes.getPreparation() , recipes.getImage());
	}
	

  
	

	
	
	
	
}
