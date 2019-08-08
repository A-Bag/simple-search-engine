package com.findwisetest.searchengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author abag
 */
public class SearchEngine {

    private final TfidfCalculator tfidfCalculator;
    private final DocumentStorage documentStorage;
    private final IndexedTermStorage indexedTermStorage;

    public SearchEngine(TfidfCalculator tfidfCalculator, DocumentStorage documentStorage, IndexedTermStorage indexedTermStorage) {
        this.tfidfCalculator = tfidfCalculator;
        this.documentStorage = documentStorage;
        this.indexedTermStorage = indexedTermStorage;
    }

    public List<Document> searchDocuments(String term) {
        Set<Integer> termIndexes = indexedTermStorage.findTermIndexes(term);

        if (termIndexes == null) {
            return new ArrayList<>();
        }

        List<Integer> sortedTermIndexes = termIndexes.stream()
                .sorted((Integer docId1, Integer docId2) -> {
                    float tfidf1 = tfidfCalculator.calculateTfidf(term, docId1);
                    float tfidf2 = tfidfCalculator.calculateTfidf(term, docId2);
                    return (int) ((tfidf2 - tfidf1) * 1000);
                })
                .collect(Collectors.toList());

        return sortedTermIndexes.stream()
                .map(documentStorage::getStoredDocument)
                .collect(Collectors.toList());

    }

}
