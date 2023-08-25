package com.CulinariaRestrita.Sg.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.CulinariaRestrita.Sg.Util.Util;
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
	

	public Page<RecipesDto> getAll() {
		PageRequest page = PageRequest.of(0, 6);
		Page<Recipes> recipesPage = recipesRepository.findAll(page);
		return Util.page(recipesPage);
	}
	public Page<RecipesDto> getAllPagenation(int pagina) {
		PageRequest page = PageRequest.of(pagina, 6);
		Page<Recipes> recipesPage = recipesRepository.findAll(page);
		return Util.page(recipesPage);
	}

	

	public String deleteRecipe(Long id) {
		recipesRepository.deleteById(id);
		return "ok";
	}

	public Recipes saveRecipe(Recipes recipes) {
		return recipesRepository.save(recipes);
	}

	
	  public Page<RecipesDto> getByType(String tipo) { 
		  PageRequest page = PageRequest.of(0, 6);
			Page<Recipes> recipesPage = recipesRepository.findByTipo(tipo, page);
			return Util.page(recipesPage);
	  }
	  
	  public Page<RecipesDto> getAllPagenationBytype(String tipo ,int pagina) {
			PageRequest page = PageRequest.of(pagina, 6);
			Page<Recipes> recipesPage = recipesRepository.findByTipo(tipo, page);
			return Util.page(recipesPage);
		}
	  
	  public List<RecipesDto> getFavoriteRecipes(Long id) { List<RecipesDto>
	 recipesByType =
	 recipesRepository.findByUserRecipe(id).stream().map(RecipesDto::new).toList();
	   return recipesByType; 
	  }
	
}
