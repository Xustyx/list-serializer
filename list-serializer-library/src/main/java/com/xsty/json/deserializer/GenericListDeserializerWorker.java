package com.xsty.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by XST on 17/09/2017.
 */
class GenericListDeserializerWorker<T>{
    private GenericListDeserializer<T> director;

    private JsonParser parser;

    private ObjectMapper mapper;
    private ArrayNode rootNode;

    private List<T> list;

    public GenericListDeserializerWorker(GenericListDeserializer<T> director) {
        this.director = director;
    }

    public List<T> work(JsonParser parser){
        this.parser = parser;

        try {
            doWork();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.list;
    }

    private void doWork() throws IOException {
        clearContext();
        loopItems();
    }

    private void clearContext() throws IOException {
        this.list = new ArrayList<T>();
        this.mapper = (ObjectMapper)parser.getCodec();
        this.rootNode = (ArrayNode)mapper.readTree(parser);
    }


    private void loopItems(){
        for (int i = 0; i < rootNode.size(); ++i) {
            workElement((ObjectNode) rootNode.get(i));
        }
    }

    private void workElement(ObjectNode element){
        Class<? extends T> clazz = getClassByIdentifier(getClassIdentifier(element));
        T classObject = getClassObject(element, clazz);

        addToList(classObject);
    }

    private String getClassIdentifier(ObjectNode element){
        return this.director.getClassIdentifier(element);
    }

    private Class<? extends T> getClassByIdentifier(String classIdentifier){
        try {
            return this.director.getClassByIdentifier(classIdentifier);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private T getClassObject(ObjectNode element, Class<? extends T> clazz){
        try {
            return mapper.readValue(element.traverse(), clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void addToList(T classObject){
        if(classObject != null)
            this.list.add(classObject);
    }
}
