package com.CulinariaRestrita.Sg;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.CulinariaRestrita.Sg.model.Recipes;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SgApplicationTests {
	
	@Autowired
	private WebTestClient testClient ;
	

	/*teste para verificar o endPoint de salvar receita*/
	@Test
	void testCreateRecipeSucess() {
		var recipes = new Recipes("teste 1" , "teste" , "teste"  );
		testClient
		.post()
		.uri("/recipes/saveRecipe")
		.bodyValue(recipes)
		.exchange()
		.expectStatus().isOk()
		.expectBody()
		.jsonPath("$").isArray()
		.jsonPath("$.lenght()").isEqualTo(1)
		.jsonPath("$[0].nameRecipe").isEqualTo(recipes.getNameRecipe())
		.jsonPath("$[1].tipo").isEqualTo(recipes.getTipo())
		.jsonPath("$[2].preparation").isEqualTo(recipes.getPreparation());
		
	}
	@Test
	void testCreateRecipeFailure() {
		var recipes = new Recipes(null , null , "teste"  );
		testClient
		.post()
		.uri("/recipes/saveRecipe")
		.bodyValue(recipes)
		.exchange()
		.expectStatus().isBadRequest();
		
	}

}
