package com.Jvnyor.openai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Jvnyor.openai.model.RequestDTO;
import com.Jvnyor.openai.model.ResponseDTO;
import com.Jvnyor.openai.service.CompletionService;

import jakarta.validation.Valid;

@RequestMapping("/v1")
@RestController
public class CompletionController {

	@Autowired
	private CompletionService service;
	
	@PostMapping("/completions")
	public ResponseEntity<ResponseDTO> completionRequest(@Valid @RequestBody RequestDTO completionRequest) {
		return ResponseEntity.ok(service.completionRequest(completionRequest));
	}

	@PostMapping("/chat/completions")
	public ResponseEntity<ResponseDTO> chatCompletionRequest(@Valid @RequestBody RequestDTO completionRequest) {
		return ResponseEntity.ok(service.chatCompletionRequest(completionRequest));
	}
}
