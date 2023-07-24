package com.CulinariaRestrita.Sg.dto;

import com.CulinariaRestrita.Sg.model.Ingredients;


public record IngredientsDto(Long id , String nameIngredient , Double value , Double quantityKg, Double quantityG, Integer quantidade) {
	public IngredientsDto(Ingredients ingredients) {
		this(ingredients.getId() , ingredients.getNameIngredient() , ingredients.getValue() , ingredients.getQuantityKg() , ingredients.getQuantityG() , ingredients.getQuantidade());
	}

	

}
