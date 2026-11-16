package com.daviddalb.enterpriseknowledgeengine.service;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import com.daviddalb.enterpriseknowledgeengine.dto.SearchQuery;
import com.daviddalb.enterpriseknowledgeengine.dto.SearchResult;

@Service
public class SearchService {

	private final VectorStore vectorStore;
	
	public SearchService(VectorStore vectorStore) {
		this.vectorStore = vectorStore;
		
		
	}
	public List<SearchResult> search(SearchQuery searchQuery) {
		List<Document> result = vectorStore.similaritySearch(SearchRequest.builder()
				.query(searchQuery.query())
				.topK(4)
				.build());
		
		return result.stream()
				.map(res -> new SearchResult(
				 res.getText(),
				 String.valueOf(res.getMetadata().get("category"))
				 ))
				.toList();
				
			
		}
	}

