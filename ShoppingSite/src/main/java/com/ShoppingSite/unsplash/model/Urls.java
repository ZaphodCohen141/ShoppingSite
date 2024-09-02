package com.ShoppingSite.unsplash.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Urls {
    @JsonProperty("regular")
    private String regular;

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }
}

