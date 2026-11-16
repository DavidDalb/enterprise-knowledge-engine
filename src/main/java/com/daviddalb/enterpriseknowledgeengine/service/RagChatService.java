package com.daviddalb.enterpriseknowledgeengine.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.daviddalb.enterpriseknowledgeengine.dto.RagResponse;
import com.daviddalb.enterpriseknowledgeengine.dto.SearchQuery;
import com.daviddalb.enterpriseknowledgeengine.dto.SearchResult;
@Service
public class RagChatService {

	private final ChatClient chatClient;
	private final SearchService searchService;
	
	public RagChatService(ChatClient.Builder chatClientBuilder, SearchService searchService) {
		this.chatClient = chatClientBuilder.build();
		this.searchService = searchService;
	}
	
	public RagResponse ask(String userMessage) {
		
		List<SearchResult> searchResults = searchService.search(new SearchQuery(userMessage));
		String context = searchResults.stream()
				.map(SearchResult::content)
				.collect(Collectors.joining("\n\n---\n\n"));
		
		String answer = chatClient.prompt()
				.system("""
                        Sei un assistente aziendale specializzato e rigoroso.
                        Rispondi alla domanda dell'utente basandoti ESCLUSIVAMENTE sulle informazioni presenti nel contesto qui sotto.
                        Se l'informazione non si trova nel contesto, rispondi: "Non trovo questa informazione nei documenti aziendali disponibili."

                        --- INIZIO CONTESTO ---
                        """ + context + """

                        --- FINE CONTESTO ---
                        """)
				.user(userMessage)
				.call()
				.content();
				
		List<String> sources = searchResults.stream()
		.map(SearchResult::category)
		.distinct()
		.toList();
		
		return new RagResponse(answer, sources);
	}
}
