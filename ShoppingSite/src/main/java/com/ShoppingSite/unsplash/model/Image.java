package com.ShoppingSite.unsplash.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Image {
    @JsonProperty("id")
    private String id;

    @JsonProperty("urls")
    private Urls urls;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }
}
