package com.xsty.json.deserializer.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Created by XST on 17/09/2017.
 */
public abstract class Animal {
    private String name;

    @JsonProperty("__metadata")
    private AnimalMetadata additionalProperties = new AnimalMetadata();

    public Animal(){
        additionalProperties.setType(classIdentifier());
    }

    public String classIdentifier(){
        return this.getClass().getSimpleName();
    }

    @JsonIgnore
    public long getChipId() {
        return additionalProperties.getId();
    }

    public void setChipId(long chipId) {
        this.additionalProperties.setId(chipId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
