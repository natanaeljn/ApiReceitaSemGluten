package com.CulinariaRestrita.Sg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.CulinariaRestrita.Sg.dto.RecipesDto;
import com.CulinariaRestrita.Sg.model.Recipes;
import com.CulinariaRestrita.Sg.repositories.RecipesRepository;
import com.CulinariaRestrita.Sg.repositories.UsersRepository;

@Service
public class RecipesService {
	
	
	
	@Autowired
	RecipesRepository recipesRepository;
	@Autowired
	UsersRepository usersRepository;
	
  public List<RecipesDto> getAll (){
	  List<RecipesDto> recipesList = recipesRepository.findAll().stream().map(RecipesDto::new).toList();
	  return recipesList;
  }
  
  public String deleteRecipe (Long id) {
	  recipesRepository.deleteById(id);
	  return "ok";
  }
  public Recipes saveRecipe(Recipes recipes) {
	 return recipesRepository.save(recipes);
  }
  public List<RecipesDto>getByType(String tipo){
	  List<RecipesDto>recipesByType = recipesRepository.findByTipo(tipo).stream().map(RecipesDto::new).toList();
	  return recipesByType;
  }
  
  public List<RecipesDto>getFavoriteRecipes(Long id){
	  List<RecipesDto>recipesByType = recipesRepository.findByUserRecipe(id).stream().map(RecipesDto::new).toList();
	  return recipesByType;
  }
  
  
  
  
  

}
