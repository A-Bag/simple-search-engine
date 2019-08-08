package com.findwisetest.searchengine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author abag
 */
public class DocumentIndexer {

    private DocumentStorage documentStorage;
    private IndexedTermStorage indexedTermStorage;
    private DocumentTokenizer documentTokenizer;

    public DocumentIndexer(DocumentStorage documentStorage, IndexedTermStorage indexedTermStorage, DocumentTokenizer documentTokenizer) {
        this.documentStorage = documentStorage;
        this.indexedTermStorage = indexedTermStorage;
        this.documentTokenizer = documentTokenizer;
    }

    public void indexDocuments() {

        Map<String, Set<Integer>> indexedTerms = new HashMap<>();

        Set<Document> storedDocuments = documentStorage.getStoredDocuments();

        storedDocuments.stream()
                .map(documentTokenizer::tokenizeDocument)
                .forEach(tokenizedDocument -> {
                    tokenizedDocument.getTerms().forEach(term -> {
                        Set<Integer> termIndexes = indexedTerms.get(term);
                        if (termIndexes == null) {
                            termIndexes = new HashSet<>();
                        }
                        termIndexes.add(tokenizedDocument.getId());
                        indexedTerms.put(term, termIndexes);
                    });
                });

        indexedTerms.forEach((key, value) -> indexedTermStorage.addIndexedTerm(key, value));
    }



}
