package com.CulinariaRestrita.Sg.dto;

import com.CulinariaRestrita.Sg.model.Recipes;


public record RecipesDto(Long id , String nameRecip , String tipo , String prep  ) {

	public RecipesDto(Recipes recipes) {
		this(recipes.getId() , recipes.getNameRecipe(), recipes.getTipo() , recipes.getPreparation());
	}
}
