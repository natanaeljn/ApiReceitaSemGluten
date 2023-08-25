package com.CulinariaRestrita.Sg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CulinariaRestrita.Sg.dto.RecipesDto;
import com.CulinariaRestrita.Sg.model.Recipes;
import com.CulinariaRestrita.Sg.model.Users;
import com.CulinariaRestrita.Sg.repositories.UsersRepository;
import com.CulinariaRestrita.Sg.services.ChatGptService;
import com.CulinariaRestrita.Sg.services.RecipesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/recipes")
public class RecipesController {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private ChatGptService chatGptService;

	@Autowired
	private RecipesService recipesService;

	@GetMapping("/getAll")
	public Page<RecipesDto> getAll() {
		return recipesService.getAll();
	}
	@GetMapping("/getAllPage/{page}")
	public Page<RecipesDto> getAllPagination(@PathVariable("page")int pagina) {
		return recipesService.getAllPagenation(pagina);
	}

	@GetMapping("/getByType/{tipo}")
	public Page<RecipesDto> getByType(@PathVariable("tipo")String tipo) {
		return recipesService.getByType(tipo);
	}

	@GetMapping("/getAllPageType/{tipo}/{page}")
	public Page<RecipesDto> getAllPagination(@PathVariable("tipo")String tipo , @PathVariable("page")int page) {
		return recipesService.getAllPagenationBytype(tipo, page);
	}
	@GetMapping("/getFavorite")
	public List<RecipesDto> getFavoritesRecipes(Long id) {
		return recipesService.getFavoriteRecipes(id);
	}

	@PostMapping("/saveRecipe")
	public Recipes saveRecipe(@RequestBody @Valid Recipes recipes, Long idUser) {
		Users users = usersRepository.findById(idUser).get();
		recipes.setUserRecipe(users);
		return recipesService.saveRecipe(recipes);
	}

	@PostMapping("/chat")
	public String generateRecipeChat(String recipe) {
		String ret = chatGptService.resposta(recipe);
		return ret;
	}

	@DeleteMapping("/delete")
	public String deleteRecipe(Long id) {
		String ret = recipesService.deleteRecipe(id);
		return ret;
	}

}
