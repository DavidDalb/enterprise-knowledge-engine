package com.daviddalb.enterpriseknowledgeengine.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daviddalb.enterpriseknowledgeengine.dto.IngestionResult;
import com.daviddalb.enterpriseknowledgeengine.service.IngestionService;

@RestController
@RequestMapping("/api/ingest")
public class IngestionController {
	
	private final IngestionService ingestionService;

	public IngestionController(IngestionService ingestionService) {
		this.ingestionService = ingestionService;
	}
	@GetMapping("/folder")
	public IngestionResult ingestFolder() throws IOException {
		 IngestionResult documents = ingestionService.ingestFromFolder();
		 return documents;
	}
	
	
}