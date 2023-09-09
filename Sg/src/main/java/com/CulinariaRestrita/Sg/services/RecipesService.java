package com.CulinariaRestrita.Sg.services;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.CulinariaRestrita.Sg.Util.Util;
import com.CulinariaRestrita.Sg.dto.RecipesDto;
import com.CulinariaRestrita.Sg.model.Recipes;
import com.CulinariaRestrita.Sg.repositories.RecipesRepository;
import com.CulinariaRestrita.Sg.repositories.UsersRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RecipesService {
	
	private static String caminhoImagem = "C://Users//T-Gamer//Documents/projetoCulinariaRestrita/";

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
	  public Recipes GetRecipe (Long id) {
		  Recipes recipes  =  this.recipesRepository.findById(id).get();
		  return recipes;
	  }
	  
	  public RecipesDto GetRecipeDto (Long id) {
		  Recipes recipes  =  this.recipesRepository.findById(id).get();
		  return Util.convertToDto(recipes);
	  }
	  public void uploadImage(Long recipeId, MultipartFile image) throws IOException {
		  byte[] bytes = image.getBytes();
  		Path caminho = Paths.get(caminhoImagem+recipeId+image.getOriginalFilename());
  		Files.write(caminho, bytes);
  		Recipes recipes = this.GetRecipe(recipeId);
  		recipes.setImage(String.valueOf(recipeId+image.getOriginalFilename()));
  		this.saveRecipe(recipes);
	    }
	  
	  public byte[] serachImg(String image) throws IOException {
		  File imagemArquivo= new File(caminhoImagem+image);
			if(image !=null ||image.trim().length()>0) {
				return Files.readAllBytes(imagemArquivo.toPath());
			}
			return null;
		}
	  
	
}
