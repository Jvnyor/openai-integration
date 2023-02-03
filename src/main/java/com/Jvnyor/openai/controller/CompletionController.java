package com.Jvnyor.openai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Jvnyor.openai.model.CompletionRequestDTO;
import com.Jvnyor.openai.model.CompletionResponseDTO;
import com.Jvnyor.openai.service.CompletionService;

import jakarta.validation.Valid;

@RequestMapping("/chat")
@RestController
public class CompletionController {

	@Autowired
	private CompletionService service;
	
	@PostMapping("/completion")
	public ResponseEntity<CompletionResponseDTO> completionRequest(@Valid @RequestBody CompletionRequestDTO completionRequest) {
		return ResponseEntity.ok(service.completionRequest(completionRequest));
	}
}
