package com.CulinariaRestrita.Sg.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.CulinariaRestrita.Sg.dto.RecipesDto;
import com.CulinariaRestrita.Sg.model.Ingredients;
import com.CulinariaRestrita.Sg.model.Recipes;
import com.CulinariaRestrita.Sg.model.Users;
import com.CulinariaRestrita.Sg.repositories.UsersRepository;
import com.CulinariaRestrita.Sg.services.ChatGptService;
import com.CulinariaRestrita.Sg.services.IngredientsService;
import com.CulinariaRestrita.Sg.services.RecipesService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/recipes")
public class RecipesController {
	
	private static String caminhoImagem = "C://Users//T-Gamer//Documents/projetoCulinariaRestrita/";
	

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private ChatGptService chatGptService;

	@Autowired
	private RecipesService recipesService;
	@Autowired
	private IngredientsService ingredientsService;
	

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
	@PostMapping("/saveRecipeWithoutImage/{email}")
	public Recipes saveRecipeWithoutImage(@RequestBody @Valid Recipes recipes, @PathVariable("email")String email) {
		Users users = usersRepository.findByemail(email);
		recipes.setUserRecipe(users);
		return recipesService.saveRecipe(recipes);
	}

	@PostMapping(value= "/saveRecipe/{email}", consumes = "multipart/form-data" )
	public Recipes saveRecipe(@RequestParam("nameRecipe") String nameRecipe,
            @RequestParam("tipo") String tipo,
            @RequestParam("preparation") String preparation,  @RequestParam("imagem") MultipartFile image , @PathVariable("email")String email) throws IOException {
		Users users = usersRepository.findByemail(email);
		Recipes recipes =  new Recipes();
		recipes.setNameRecipe(nameRecipe);
		recipes.setTipo(tipo);
		recipes.setPreparation(preparation);
		recipes.setUserRecipe(users);
		recipesService.saveRecipe(recipes);
		if(!image.isEmpty()||image!= null) {
		try {
		recipesService.uploadImage(recipes.getId(), image);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		}
		
		return recipes;
	}

	@GetMapping("/chat/{ing}")
	public String generateRecipeChat(@PathVariable("ing") String recipe) {
		String ret = chatGptService.resposta(recipe , 'g');
		return ret;
	}
	@GetMapping("/chatLac/{ing}")
	public String generateRecipeChatLac(@PathVariable("ing") String recipe) {
		String ret = chatGptService.resposta(recipe , 'l');
		return ret;
	}

	@DeleteMapping("/delete")
	public String deleteRecipe(Long id) {
		String ret = recipesService.deleteRecipe(id);
		return ret;
	}
	
	
	@PostMapping("saveImg/{recipeId}/upload-image")
    public ResponseEntity<String> uploadImage(@PathVariable Long recipeId, @RequestParam("image") MultipartFile image) {
        try {
        	if(!image.isEmpty()) {
        		this.recipesService.uploadImage(recipeId, image);
        		 return ResponseEntity.ok("Imagem salva com sucesso.");
        	}
        	return ResponseEntity.ok("Imagem nao salva .");
          
           
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar a imagem.");
        }
    }
	
	@GetMapping("searchImage/{image}")
	@ResponseBody
	public  byte[] retornarImage(@PathVariable("image")String image) throws IOException {
		return this.recipesService.serachImg(image);
	}
	@GetMapping("/getRecipe/{recipeId}")
	public RecipesDto getRecipe(@PathVariable("recipeId")Long id) {
		return recipesService.GetRecipeDto(id);
	}
	

}
