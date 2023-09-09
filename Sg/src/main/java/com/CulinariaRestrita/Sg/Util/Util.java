package com.CulinariaRestrita.Sg.Util;

import org.springframework.data.domain.Page;

import com.CulinariaRestrita.Sg.dto.RecipesDto;
import com.CulinariaRestrita.Sg.model.Recipes;

public class Util {

	public static Page<RecipesDto> page ( Page<Recipes> recipeCon) {
		return recipeCon.map(recipe -> new RecipesDto(
	            recipe.getId(),
	            recipe.getNameRecipe(),
	            recipe.getTipo(),
	            recipe.getPreparation(),
	            recipe.getImage()
	        ));
	}
	
	public static RecipesDto convertToDto(Recipes recipe) {
	    return new RecipesDto(
	        recipe.getId(),
	        recipe.getNameRecipe(),
	        recipe.getTipo(),
	        recipe.getPreparation(),
	        recipe.getImage()
	     );
	
}
	
}
