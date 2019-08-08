package com.findwisetest.searchengine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author abag
 */
public class TfIdfCalculatorTest {

    private DocumentStorage documentStorage = new DocumentStorage();
    private DocumentTokenizer documentTokenizer = new DocumentTokenizer();

    private TfidfCalculator tfidfCalculator = new TfidfCalculator(documentStorage, documentTokenizer);

    @Test
    public void calculateTfidf__brown() {

        // given1,
        documentStorage.addDocument(new Document(1, "the brown fox jumped over the brown dog"));
        documentStorage.addDocument(new Document(2, "the lazy brown dog sat in the corner"));
        documentStorage.addDocument(new Document(3, "the red fox bit the lazy dog"));

        // execute
        float tfidf1 = tfidfCalculator.calculateTfidf("brown", 1);
        float tfidf2 = tfidfCalculator.calculateTfidf("brown", 2);

        // assert
        assertEquals((float)0.044, tfidf1);
        assertEquals((float)0.022, tfidf2);
    }
}
