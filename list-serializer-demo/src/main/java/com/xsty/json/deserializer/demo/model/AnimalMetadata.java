package com.xsty.json.deserializer.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by XST on 17/09/2017.
 */
public class AnimalMetadata {

    @JsonProperty("#type")
    private String type;

    @JsonProperty("#id")
    private long id;

    @JsonProperty("#type")
    public String getType() {
        return type;
    }

    @JsonProperty("#type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("#id")
    public long getId() {
        return id;
    }

    @JsonProperty("#id")
    public void setId(long id) {
        this.id = id;
    }
}
