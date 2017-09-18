package com.xsty.json.deserializer.demo.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.xsty.json.deserializer.demo.util.AnimalDeserializer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XST on 17/09/2017.
 */
public class Zoo {
    private String city;
    private String phoneNumber;

    @JsonDeserialize(using = AnimalDeserializer.class)
    private List<Animal> animals = new ArrayList<Animal>();


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
}
