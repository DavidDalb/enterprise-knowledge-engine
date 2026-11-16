package com.daviddalb.enterpriseknowledgeengine.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import com.daviddalb.enterpriseknowledgeengine.dto.IngestionResult;

@Service
public class IngestionService {

	private final VectorStore vectorStore;
    // Cartella che contiene i PDF da leggere
    private static final String FOLDER_PATH =
            "C:\\Users\\Davide\\Desktop\\Fullstack_development\\document_for_testing";

    public IngestionService(VectorStore vectorStore) {
    	this.vectorStore = vectorStore;
    }
    
//  TEMPORANEA: allo Step 4 la sostituisce l'AI)
private static final Map<String, String> CATEGORY_BY_FILE = Map.of(
"8.-DiCocco-Zentai_def.pdf",                        "ARTICLE",
"Di_Carlo_-_Licenze_Open_Source.pdf",               "REPORT",
"GAMES-Lizenzbed_IT_2025.pdf",                      "CONTRACT",
"HUAWEI MateBook D 14 2024 12th Gen Core Guida utente-(MDF-X,Windows11_01,it).pdf", "PROCEDURE",
"Regolamento UE 2016 679. Arricchito con riferimenti ai Considerando Aggiornato alle rettifiche pubblicate sulla Gazzetta Ufficiale  dell'Unione europea 127 del 23 maggio 20.pdf", "POLICY",
"tomb-raider-iii-eula-italian.pdf",                 "CONTRACT"
);
    /**
     * Legge tutti i PDF presenti nella cartella e restituisce
     * la lista dei documenti estratti da Tika.
     */
    private List<Document> readAllPdf() throws IOException {

        // Lista finale con tutti i documenti trovati
        List<Document> allDocuments = new ArrayList<>();

        // 1) Trasformo il percorso testuale in un oggetto Path (NIO)
        Path folder = Paths.get(FOLDER_PATH);

        // 2) Apro uno stream di file contenuti nella cartella
        //    (try-with-resources chiude automaticamente lo stream)
        try (Stream<Path> files = Files.list(folder)) {

            files
                // 3) Tengo solo i file (non le sottocartelle)
                .filter(Files::isRegularFile)
                // 4) Tengo solo quelli che finiscono con .pdf (case-insensitive)
                .filter(path -> path.getFileName().toString().toLowerCase().endsWith(".pdf"))
                // 5) Per ogni PDF leggo il contenuto con Tika
                .forEach(pdfPath -> {
                    // FileSystemResource avvolge il file del filesystem
                    FileSystemResource resource = new FileSystemResource(pdfPath);

                    // TikaDocumentReader estrae il testo dal PDF
                    TikaDocumentReader reader = new TikaDocumentReader(resource);

                    // get() restituisce la lista di Document letti dal reader
                    allDocuments.addAll(reader.get());
                });
        }

        // 6) Ritorno tutti i documenti trovati
        return allDocuments;
    }
    private List<Document> splitDocuments(List<Document> documents) {
       TokenTextSplitter splitter = TokenTextSplitter.builder().build();
	   return splitter.apply(documents);
    }
    
    private void addCategoryMetadata(List<Document> chunks) {
        for (Document chunk : chunks) {
            String fileName = String.valueOf(chunk.getMetadata().get("file_name"));
            chunk.getMetadata().put("category", CATEGORY_BY_FILE.getOrDefault(fileName, "OTHER"));
        }
    }
    public IngestionResult ingestFromFolder () throws IOException {
    	List<Document> documents = readAllPdf();
    	List<Document> chunks = splitDocuments(documents);
    	addCategoryMetadata(chunks);
    	vectorStore.add(chunks);
    	return new IngestionResult(documents.size(), chunks.size());
    }
}
