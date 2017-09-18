package com.xsty.json.deserializer.demo.util;

import com.xsty.json.deserializer.GenericListRegistry;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by XST on 17/09/2017.
 */
public class GenericSubclassPackageFinder<T> {

    private String packageRoute = "com.xsty.json.deserializer.demo.model";
    private Class<? extends T> clazz;

    private List<GenericListRegistry<T>> registries = new ArrayList<GenericListRegistry<T>>();

    public GenericSubclassPackageFinder(Class<? extends T> clazz){
        this.clazz = clazz;
        findClasses();
    }

    public List<GenericListRegistry<T>> getRegistries() {
        return registries;
    }

    private void findClasses(){
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);
        provider.addIncludeFilter(new AssignableTypeFilter(clazz));
        Set<BeanDefinition> components = provider.findCandidateComponents(packageRoute);

        for(BeanDefinition component: components){
            String name = component.getBeanClassName();

            try {
                Class<? extends T> clazz = (Class<? extends T>)Class.forName(name);
                registries.add(new GenericListRegistry<>(clazz.getSimpleName(),clazz));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
