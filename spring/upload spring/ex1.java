package com.example.demo;

public class ex1{
	private final long id;
    private final String content;

    public ex1(long id, String content) {
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