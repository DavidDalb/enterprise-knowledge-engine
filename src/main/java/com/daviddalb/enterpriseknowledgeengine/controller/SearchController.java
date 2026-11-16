package com.daviddalb.enterpriseknowledgeengine.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daviddalb.enterpriseknowledgeengine.dto.SearchQuery;
import com.daviddalb.enterpriseknowledgeengine.dto.SearchResult;
import com.daviddalb.enterpriseknowledgeengine.service.SearchService;

@RestController
@RequestMapping("/api/search")
public class SearchController {

	private final SearchService searchService;

	public SearchController(SearchService searchService) {
		this.searchService = searchService;
	}
	@PostMapping
	public List<SearchResult> search(@RequestBody SearchQuery searchQuery) {
		return searchService.search(searchQuery);
	}
	
}
