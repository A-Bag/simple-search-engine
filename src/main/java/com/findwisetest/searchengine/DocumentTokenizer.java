package com.findwisetest.searchengine;

import java.util.Arrays;
import java.util.List;

/**
 * @author abag
 */
public class DocumentTokenizer {

    public TokenizedDocument tokenizeDocument(Document document) {
        List<String> terms = Arrays.asList(document.getText().split(" "));
        return new TokenizedDocument(document.getId(), terms);
    }
}
