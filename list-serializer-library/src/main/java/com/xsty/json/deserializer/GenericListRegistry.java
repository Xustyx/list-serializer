package com.xsty.json.deserializer;

/**
 * Created by XST on 17/09/2017.
 */
public class GenericListRegistry<T> {
    private String classIdentifer;
    private Class<? extends T> clazz;

    public GenericListRegistry(String classIdentifer, Class<? extends T> clazz) {
        this.classIdentifer = classIdentifer;
        this.clazz = clazz;
    }

    public String getClassIdentifer() {
        return classIdentifer;
    }

    public Class<? extends T> getClazz() {
        return clazz;
    }

}
