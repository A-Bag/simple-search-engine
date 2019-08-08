package com.findwisetest.searchengine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

/**
 * @author abag
 */
public class SearchEngineTest {

    private DocumentStorage documentStorage = new DocumentStorage();
    private IndexedTermStorage indexedTermStorage = new IndexedTermStorage();
    private DocumentTokenizer documentTokenizer = new DocumentTokenizer();
    private TfidfCalculator tfidfCalculator = new TfidfCalculator(documentStorage, documentTokenizer);
    private DocumentIndexer documentIndexer = new DocumentIndexer(documentStorage, indexedTermStorage, documentTokenizer);

    private SearchEngine searchEngine = new SearchEngine(tfidfCalculator, documentStorage, indexedTermStorage);

    private Document document1 = new Document(1, "the brown fox jumped over the brown dog");
    private Document document2 = new Document(2, "the lazy brown dog sat in the corner");
    private Document document3 = new Document(3, "the red fox bit the lazy dog");

    @BeforeEach
    public void beforeEach() {
        documentStorage.addDocument(document1);
        documentStorage.addDocument(document2);
        documentStorage.addDocument(document3);
        documentIndexer.indexDocuments();
    }

    @Test
    public void searchDocuments__brown() {

        // execute
        List<Document> foundDocuments = searchEngine.searchDocuments("brown");

        // assert
        assertThat(foundDocuments, hasSize(2));
        assertThat(foundDocuments, contains(document1, document2));
    }

    @Test
    public void searchDocuments__fox() {

        // execute
        List<Document> foundDocuments = searchEngine.searchDocuments("fox");

        // assert
        assertThat(foundDocuments, hasSize(2));
        assertThat(foundDocuments, contains(document3, document1));
    }

}
