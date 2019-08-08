package com.findwisetest.searchengine;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author abag
 */
public class DocumentStorage {

    private Map<Integer, String> storedDocuments = new HashMap<>();

    public Document getStoredDocument(int documentId) {
        return new Document(documentId, storedDocuments.get(documentId));
    }

    public Set<Document> getStoredDocuments() {
        return storedDocuments.entrySet().stream()
                .map(entry -> new Document(entry.getKey(), entry.getValue()))
                .collect(Collectors.toSet());
    }

    public void addDocument(Document document) {
        storedDocuments.put(document.getId(), document.getText());
    }
}
