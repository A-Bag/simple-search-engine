package com.findwisetest.searchengine;

import java.util.Objects;

/**
 * @author abag
 */
public class Document {

    private int id;
    private String text;

    public Document(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(text, document.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
