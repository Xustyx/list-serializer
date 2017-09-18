package com.xsty.json.deserializer.demo.util;


import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xsty.json.deserializer.GenericListDeserializer;
import com.xsty.json.deserializer.GenericListRegistry;
import com.xsty.json.deserializer.demo.model.Animal;

import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Created by XST on 17/09/2017.
 */
@Component
public class AnimalDeserializer extends GenericListDeserializer<Animal> {

    public AnimalDeserializer(){
        super(Animal.class);
    }

    @Override
    protected List<GenericListRegistry<Animal>> registryList() {
        return new GenericSubclassPackageFinder<Animal>(Animal.class).getRegistries();
    }

    @Override
    protected String getClassIdentifier(ObjectNode element) {
        return element.findValue("__metadata").findValue("#type").asText();
    }
}
