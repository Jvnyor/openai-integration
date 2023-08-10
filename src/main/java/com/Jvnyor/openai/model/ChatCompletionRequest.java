package com.Jvnyor.openai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@AllArgsConstructor
@Builder
public class ChatCompletionRequest {
    @JsonProperty("model")
    public String model;
    @JsonProperty("messages")
    public List<Message> messages;
}
