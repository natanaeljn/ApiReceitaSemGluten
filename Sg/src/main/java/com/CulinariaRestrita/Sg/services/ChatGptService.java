package com.CulinariaRestrita.Sg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;

@Service
public class ChatGptService {

	/* esta chave da api do chat,se obtem pelo proprio site */
	@Value("${chatgpt.apiKey}")
	private String apiKey;

	OpenAiService service = new OpenAiService("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");

	public String resposta(String relacao , char tipo) {
		String question = "";
		if(tipo == 'g') {
			question = "Escreva uma receita sem gluten relacionada com :";
		}
		else if (tipo=='l') {
			question = "Escreva uma receita sem lactose relacionada com :";
		}
		
		
		CompletionRequest request = CompletionRequest.builder()
				/*
				 * se refere ao modelo de dados q usaremos,ha modelos especificos para calculos
				 */
				.model("text-davinci-003").prompt(question + relacao)
				/* maximo de caracteres do retorno da resposta */
				.maxTokens(3000).build();

		String resp = service.createCompletion(request).getChoices().get(0).getText();
		return resp;
	}

}
