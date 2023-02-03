package com.Jvnyor.openai.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.Jvnyor.openai.model.Choice;
import com.Jvnyor.openai.model.CompletionRequest;
import com.Jvnyor.openai.model.CompletionRequestDTO;
import com.Jvnyor.openai.model.CompletionResponse;
import com.Jvnyor.openai.model.CompletionResponseDTO;
import com.Jvnyor.openai.service.exception.BadRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CompletionService {
	@Value("${openai.api.url}")
	private String URL;
	@Value("${openai.api.key}")
	private String apiKey;

	public CompletionResponseDTO completionRequest(CompletionRequestDTO completionRequestDTO) {
		CompletionRequest completionRequest = CompletionRequest.builder()
				.model("text-davinci-003")
				.prompt(completionRequestDTO.getPrompt())
				.maxTokens(1000)
				.temperature(0)
				.build();
		CompletionResponse completionResponse = null;
		String jsonCompletionRequest = null;
		try {
			jsonCompletionRequest = new ObjectMapper().writeValueAsString(completionRequest);

			log.info("Json request: {}", jsonCompletionRequest);
			HttpResponse<JsonNode> response = Unirest.post(URL)
					.header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + apiKey)
					.body(jsonCompletionRequest)
					.asJson();
			log.info("Json response: {}", response.getBody().toString());
			completionResponse = new ObjectMapper().readValue(response.getBody().toString(), CompletionResponse.class);
		} catch (JsonMappingException e) {
			throw new BadRequestException("Json error, check your json request");
		} catch (JsonProcessingException e) {
			throw new BadRequestException("Json error, check your json request");
		}
		CompletionResponseDTO completionResponseDTO = null;
		for (Choice choice : completionResponse.getChoices()) {
			completionResponseDTO = CompletionResponseDTO.builder()
					.text(choice.getText())
					.build();
		}
		return completionResponseDTO;
	}
}