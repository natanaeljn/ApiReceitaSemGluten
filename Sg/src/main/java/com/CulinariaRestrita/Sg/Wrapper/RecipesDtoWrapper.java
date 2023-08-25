package com.CulinariaRestrita.Sg.Wrapper;

import com.CulinariaRestrita.Sg.dto.RecipesDto;
import com.CulinariaRestrita.Sg.model.Recipes;

public class RecipesDtoWrapper {
	private final RecipesDto recipesDto;

    public RecipesDtoWrapper() {
        this.recipesDto = new RecipesDto(null, null, null, null);
    }

    public RecipesDto getRecipesDto() {
        return recipesDto;
    }

    public RecipesDtoWrapper(Recipes recipes) {
        this.recipesDto = new RecipesDto(
            recipes.getId(),
            recipes.getNameRecipe(),
            recipes.getTipo(),
            recipes.getPreparation()
        );
    }
}
