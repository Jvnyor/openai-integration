
package com.Jvnyor.openai.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "prompt_tokens", "completion_tokens", "total_tokens" })
public class Usage {

    @JsonProperty("prompt_tokens")
    public Integer promptTokens;
    @JsonProperty("completion_tokens")
    public Integer completionTokens;
    @JsonProperty("total_tokens")
    public Integer totalTokens;

}
