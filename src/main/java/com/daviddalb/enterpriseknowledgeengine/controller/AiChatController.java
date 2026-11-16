package com.daviddalb.enterpriseknowledgeengine.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daviddalb.enterpriseknowledgeengine.dto.ChatRequest;
import com.daviddalb.enterpriseknowledgeengine.dto.ChatResponse;
import com.daviddalb.enterpriseknowledgeengine.service.AiChatService;

@RestController
@RequestMapping("/api/chat")
public class AiChatController {
	
	private final AiChatService aiChatService;
	
	public AiChatController(AiChatService aiChatService) {
		this.aiChatService = aiChatService;
	}

	@PostMapping
	public ChatResponse chat(@RequestBody ChatRequest request) {
		return new ChatResponse(aiChatService.chat(request.message()));
	
	 }
	}
