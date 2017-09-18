package com.xsty.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.List;

/**
 * Created by XST on 17/09/2017.
 */
public abstract class GenericListDeserializer<T> extends StdDeserializer<List<T>> {
    private GenericListRegister<T> register;
    private GenericListDeserializerWorker<T> worker;

    protected GenericListDeserializer(Class<?> vc) {
        super(vc);
        setUp();
    }


    @Override
    public List<T> deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return this.worker.work(parser);
    }


    private void setUp(){
        this.register = new GenericListRegister<T>();
        this.worker = new GenericListDeserializerWorker<>(this);

        fillRegistry();
    }

    private void fillRegistry(){
        for(GenericListRegistry<T> registry: registryList()){
            addRegistry(registry);
        }
    }

    private void addRegistry(GenericListRegistry<T> registry){
        this.register.register(registry);
    }

    protected Class<? extends T> getClassByIdentifier(String identifier) throws ClassNotFoundException {
        return this.register.find(identifier);
    }

    /**
     * Must return a list with classes to register.
     * @return A GenericListRegistry of (T)
     */
    protected abstract List<GenericListRegistry<T>> registryList();

    /**
     * Must return an identifier to find class on registry.
     * @param element Current element to inspect.
     * @return Class identifier.
     */
    protected abstract String getClassIdentifier(ObjectNode element);

}