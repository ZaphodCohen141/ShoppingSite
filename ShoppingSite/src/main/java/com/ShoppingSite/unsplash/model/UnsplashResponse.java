package com.ShoppingSite.unsplash.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UnsplashResponse {
    @JsonProperty("results")
    private List<Image> results;

    public List<Image> getResults() {
        return results;
    }

    public void setResults(List<Image> results) {
        this.results = results;
    }
}
