package com.Jvnyor.openai.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.Jvnyor.openai.model.CompletionRequest;
import com.Jvnyor.openai.model.CompletionRequestDTO;
import com.Jvnyor.openai.model.CompletionResponse;
import com.Jvnyor.openai.model.CompletionResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
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
	
	public CompletionResponseDTO completionRequest(CompletionRequestDTO completionRequestDTO) throws JsonProcessingException {
		CompletionRequest completionRequest = CompletionRequest.builder()
				.model("text-davinci-003")
				.prompt(completionRequestDTO.getPrompt())
				.maxTokens(1000)
				.temperature(0)
				.build();
		String jsonCompletionRequest = new ObjectMapper().writeValueAsString(completionRequest);
		log.info("Request: {}", jsonCompletionRequest);
		HttpResponse<JsonNode> response = Unirest.post(URL)
		.header("Content-Type", "application/json")
		.header("Authorization", "Bearer " + apiKey)
		.body(jsonCompletionRequest)
		.asJson();
		log.info("Response: {}", response.getBody().toString());
		CompletionResponse completionResponse = new ObjectMapper().readValue(response.getBody().toString(), CompletionResponse.class);
		return CompletionResponseDTO.builder()
				.text(completionResponse.getChoices().get(0).getText())
				.build();
	}
}