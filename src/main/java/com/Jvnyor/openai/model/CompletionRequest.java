package com.Jvnyor.openai.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class CompletionRequest {

	@JsonProperty("model")
	public String model;
	@JsonProperty("prompt")
	public String prompt;
	@JsonProperty("max_tokens")
	public Integer maxTokens;
	@JsonProperty("temperature")
	public Integer temperature;

}