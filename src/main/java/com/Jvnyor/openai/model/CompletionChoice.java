
package com.Jvnyor.openai.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "text", "index", "logprobs", "finish_reason" })
public class CompletionChoice {

    @JsonProperty("text")
    public String text;
    @JsonProperty("index")
    public Integer index;
    @JsonProperty("logprobs")
    public String logprobs;
    @JsonProperty("finish_reason")
    public String finishReason;

}
