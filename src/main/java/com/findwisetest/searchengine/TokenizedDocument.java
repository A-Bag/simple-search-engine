package com.findwisetest.searchengine;

import java.util.List;

/**
 * @author abag
 */
public class TokenizedDocument {

    private int id;
    private List<String> terms;

    public TokenizedDocument(int id, List<String> terms) {
        this.id = id;
        this.terms = terms;
    }

    public int getId() {
        return id;
    }

    public List<String> getTerms() {
        return terms;
    }
}
