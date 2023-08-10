package com.Jvnyor.openai.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestDTO {

	@NotNull(message = "Field cannot be null")
	@NotBlank(message = "Field cannot be empty or blank")
	private String prompt;
}
