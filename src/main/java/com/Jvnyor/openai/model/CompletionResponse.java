
package com.Jvnyor.openai.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "object", "created", "model", "choices", "usage" })
public class CompletionResponse {

    @JsonProperty("id")
    public String id;
    @JsonProperty("object")
    public String object;
    @JsonProperty("created")
    public Integer created;
    @JsonProperty("model")
    public String model;
    @JsonProperty("choices")
    public List<CompletionChoice> choices;
    @JsonProperty("usage")
    public Usage usage;

}
