package com.xsty.json.deserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by XST on 17/09/2017.
 */
class GenericListRegister<T> {
    private Map<String, Class<? extends T>> registry = new HashMap<String,  Class<? extends T>>();

    public boolean register(GenericListRegistry registry) throws IllegalArgumentException {
        String identifier = registry.getClassIdentifer();
        Class<? extends T> clazz = registry.getClazz();

        if(identifier == null || identifier.isEmpty())
            throw new IllegalArgumentException("Identifier cannot be null or blank.");


        if(clazz == null)
            throw new IllegalArgumentException("Class cannot be null.");

        if(!exist(identifier))
            this.registry.put(identifier, clazz);

        return true;
    }


    public Class<? extends T> find(String identifier) throws ClassNotFoundException {
        if(!exist(identifier)){
            throw new ClassNotFoundException("Class with identifier [" + identifier + "] was not found.");
        }

        return this.registry.get(identifier);
    }

    public void remove(String identifier){
        this.registry.remove(identifier);
    }

    public boolean exist(String identifier){
       return this.registry.containsKey(identifier);
    }

}
