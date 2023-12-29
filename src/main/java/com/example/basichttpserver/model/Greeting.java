package com.example.basichttpserver.model;

/**
 * Model Layer :
 * Represents data and its state.
 * This layer usually contains plain Java objects (POJOs) that represent the entities in your application.
 */
public class Greeting {
    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
