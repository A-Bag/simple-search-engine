package com.findwisetest.searchengine;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author abag
 */
public class IndexedTermStorage {

    private final Map<String, Set<Integer>> indexedTerms = new HashMap<>();

    public Set<Integer> findTermIndexes(String term) {
        return indexedTerms.get(term);
    }

    public Map<String, Set<Integer>> getIndexedTerms() {
        return indexedTerms;
    }

    public void addIndexedTerm(String term, Set<Integer> indexes) {
        indexedTerms.put(term, indexes);
    }

}
