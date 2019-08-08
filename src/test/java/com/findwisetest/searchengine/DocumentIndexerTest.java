package com.findwisetest.searchengine;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author abag
 */
public class DocumentIndexerTest {

    private DocumentStorage documentStorage = new DocumentStorage();
    private IndexedTermStorage indexedTermStorage = new IndexedTermStorage();
    private DocumentTokenizer documentTokenizer = new DocumentTokenizer();

    private DocumentIndexer documentIndexer = new DocumentIndexer(documentStorage, indexedTermStorage, documentTokenizer);

    @Test
    public void indexDocuments() {

        // given
        documentStorage.addDocument(new Document(1, "findwise company findwise"));
        documentStorage.addDocument(new Document(2, "findwise java developer"));

        // execute
        documentIndexer.indexDocuments();

        // assert
        Map<String, Set<Integer>> indexedTerms = indexedTermStorage.getIndexedTerms();
        assertEquals(4, indexedTerms.keySet().size());
        assertThat(indexedTerms.get("findwise"), containsInAnyOrder(1, 2));
        assertThat(indexedTerms.get("company"), contains(1));
        assertThat(indexedTerms.get("java"), contains(2));
        assertThat(indexedTerms.get("developer"), contains(2));

    }
}
