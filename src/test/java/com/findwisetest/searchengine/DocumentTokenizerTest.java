package com.findwisetest.searchengine;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author abag
 */
public class DocumentTokenizerTest {

    private DocumentTokenizer documentTokenizer = new DocumentTokenizer();

    @Test
    public void tokenizeDocument() {

        // given
        Document document = new Document(1, "the red fox bit the lazy dog");

        // execute
        TokenizedDocument tokenizedDocument = documentTokenizer.tokenizeDocument(document);

        // assert
        assertEquals(1, tokenizedDocument.getId());
        assertThat(tokenizedDocument.getTerms(), contains("the", "red", "fox", "bit", "the", "lazy", "dog"));
    }
}
