package com.daviddalb.enterpriseknowledgeengine.dto;

import java.util.List;

public record RagResponse (
		String answer,
		List<String> sources) {}