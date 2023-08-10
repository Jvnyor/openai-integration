package com.Jvnyor.openai.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "object",
        "created",
        "choices",
        "usage"
})
public class ChatCompletionResponse {

    @JsonProperty("id")
    public String id;
    @JsonProperty("object")
    public String object;
    @JsonProperty("created")
    public int created;
    @JsonProperty("choices")
    public List<ChatCompletionChoice> choices;
    @JsonProperty("usage")
    public Usage usage;

}