package com.findwisetest.searchengine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author abag
 */
public class TfidfCalculator {

    private final DocumentStorage documentStorage;
    private final DocumentTokenizer documentTokenizer;

    public TfidfCalculator(DocumentStorage documentStorage, DocumentTokenizer documentTokenizer) {
        this.documentStorage = documentStorage;
        this.documentTokenizer = documentTokenizer;
    }

    public float calculateTfidf(String term, int documentId) {
        float tf = calculateTf(term, documentId);
        float idf = calculateIdf(term);

        return round(tf * idf, 3);
    }

    private float calculateTf(String term, int documentId) {
        TokenizedDocument document = documentTokenizer.tokenizeDocument(documentStorage.getStoredDocument(documentId));

        float termOccurencesInDocument = document.getTerms().stream()
                .filter(t -> t.equals(term))
                .count();
        float allTermsInDocument = document.getTerms().size();

        return termOccurencesInDocument / allTermsInDocument;
    }

    private float calculateIdf(String term) {
        Set<TokenizedDocument> tokenizedDocuments = documentStorage.getStoredDocuments().stream()
                .map(documentTokenizer::tokenizeDocument)
                .collect(Collectors.toSet());

        float documentsWhereTermOccurs = tokenizedDocuments.stream()
                .filter(doc -> doc.getTerms().contains(term))
                .count();

        return (float)Math.log10(tokenizedDocuments.size() / documentsWhereTermOccurs);
    }

    private float round(float value, int places) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.floatValue();
    }
}
