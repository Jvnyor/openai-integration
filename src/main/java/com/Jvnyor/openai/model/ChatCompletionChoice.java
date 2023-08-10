package com.Jvnyor.openai.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "index",
        "message",
        "finish_reason"
})
public class ChatCompletionChoice {
    @JsonProperty("index")
    public int index;
    @JsonProperty("message")
    public Message message;
    @JsonProperty("finish_reason")
    public String finishReason;
}
