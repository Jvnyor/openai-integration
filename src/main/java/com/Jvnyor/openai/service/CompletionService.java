package com.Jvnyor.openai.service;

import com.Jvnyor.openai.model.*;
import com.Jvnyor.openai.service.exception.BadRequestException;
import com.Jvnyor.openai.service.exception.UserException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class CompletionService {
	@Value("${openai.api.url}")
	private String URL;
	@Value("${openai.api.key}")
	private String apiKey;

	public ResponseDTO chatCompletionRequest(RequestDTO requestDTO) {
		Message message = Message.builder()
				.role("user")
				.content(requestDTO.getPrompt())
				.build();
		List<Message> messages = Collections.singletonList(message);
		ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
				.model("gpt-3.5-turbo")
				.messages(messages)
				.build();
		ChatCompletionResponse chatCompletionResponse = null;
		String jsonChatCompletionRequest = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			jsonChatCompletionRequest = objectMapper.writeValueAsString(chatCompletionRequest);

			log.info("Json request: {}", jsonChatCompletionRequest);
			HttpResponse<JsonNode> response = Unirest.post(URL + "/chat/completions")
					.header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + apiKey)
					.body(jsonChatCompletionRequest)
					.asJson();
			log.info("Response status: {}, Response JSON: {}", response.getStatus(), response.getBody().toString());
			if (!response.isSuccess()) {
				throw new UserException(JsonPath.parse(response.getBody().toString()).read("$.error.message"), JsonPath.parse(response.getBody().toString()).read("$.error.type"), response.getStatus());
			}
			chatCompletionResponse = objectMapper.readValue(response.getBody().toString(), ChatCompletionResponse.class);
			log.info("chatCompletionResponse={}", chatCompletionResponse);
		} catch (JsonProcessingException e) {
			throw new BadRequestException("Json error, check your request");
		}
		ResponseDTO responseDTO = null;
		for (ChatCompletionChoice chatCompletionChoice : chatCompletionResponse.getChoices()) {
			responseDTO = ResponseDTO.builder()
					.text(chatCompletionChoice.getMessage().getContent())
					.build();
		}
		return responseDTO;
	}

	public ResponseDTO completionRequest(RequestDTO requestDTO) {
		CompletionRequest completionRequest = CompletionRequest.builder()
				.model("text-davinci-003")
				.prompt(requestDTO.getPrompt())
				.maxTokens(1000)
				.temperature(0)
				.build();
		CompletionResponse completionResponse = null;
		String jsonCompletionRequest = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			jsonCompletionRequest = objectMapper.writeValueAsString(completionRequest);

			log.info("Json request: {}", jsonCompletionRequest);
			HttpResponse<JsonNode> response = Unirest.post(URL + "/completions")
					.header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + apiKey)
					.body(jsonCompletionRequest)
					.asJson();
			log.info("Response status: {}, Response JSON: {}", response.getStatus(), response.getBody().toString());
			if (!response.isSuccess()) {
				throw new UserException(JsonPath.parse(response.getBody().toString()).read("$.error.message"), JsonPath.parse(response.getBody().toString()).read("$.error.type"), response.getStatus());
			}
			completionResponse = objectMapper.readValue(response.getBody().toString(), CompletionResponse.class);
			log.info("completionResponse={}", completionResponse);
		} catch (JsonProcessingException e) {
			throw new BadRequestException("Json error, check your json request");
		}
		ResponseDTO responseDTO = null;
		for (CompletionChoice completionChoice : completionResponse.getChoices()) {
			responseDTO = ResponseDTO.builder()
					.text(completionChoice.getText())
					.build();
		}
		return responseDTO;
	}
}