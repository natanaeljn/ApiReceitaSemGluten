package com.CulinariaRestrita.Sg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CulinariaRestrita.Sg.dto.IngredientsDto;
import com.CulinariaRestrita.Sg.model.Ingredients;
import com.CulinariaRestrita.Sg.model.Recipes;
import com.CulinariaRestrita.Sg.repositories.RecipesRepository;
import com.CulinariaRestrita.Sg.services.IngredientsService;
import com.CulinariaRestrita.Sg.services.RecipesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("ingredients")
public class IngredientsController {
	@Autowired
	private RecipesRepository recipesRepository;

	@Autowired
	private IngredientsService ingredientsService;

	@GetMapping("/getAll")
	public List<IngredientsDto> getAll() {
		return ingredientsService.getAll();
	}

	@GetMapping("/getByRecipe/{recipeId}")
	public List<IngredientsDto> getByType( @PathVariable("recipeId")Long idRecipe) {
		return ingredientsService.getAllByRecipe(idRecipe);
	}

	@PostMapping("/saveIngredient/{idRecipe}")
	public Ingredients saveIngredient(@RequestBody @Valid Ingredients ing , @PathVariable("idRecipe")Long idRecipe) {
		Recipes recipes = recipesRepository.findById(idRecipe).get();
		ing.setRecipies(recipes);
		return ingredientsService.saveIngredient(ing);
	}

}
