package com.xsty.json.deserializer.demo.service;

import com.xsty.json.deserializer.demo.model.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by XST on 17/09/2017.
 */
@Service
public class ZooService {
    private Zoo zoo = new Zoo();
    private long chipGenerator = 0;

    public Zoo getZoo() {
        return zoo;
    }

    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    @PostConstruct
    private void init(){
        zoo.setCity("Zooland");
        zoo.setPhoneNumber("987-654-321");

        addElephant("Manny",23);
        addElephant("Ellie", 18);
        addLion("Simba","Brown");
        addLion("Scar","Black");
        addTiger("Shere Khan",54);
        addTiger("Rajah",45);
    }

    private void addElephant(String name, int tuskSize){
        Elephant elephant = new Elephant();
        elephant.setName(name);
        elephant.setTuskSize(tuskSize);

        add(elephant);
    }

    private void addLion(String name, String maneColor){
        Lion lion = new Lion();
        lion.setName(name);
        lion.setManeColor(maneColor);

        add(lion);
    }

    private void addTiger(String name, int stripeNumber){
        Tiger tiger = new Tiger();
        tiger.setName(name);
        tiger.setStripeNumber(stripeNumber);

        add(tiger);
    }


    public long getChipGenerator() {
        return chipGenerator;
    }

    public void add(Animal animal){
        animal.setChipId(++chipGenerator);
        zoo.getAnimals().add(animal);
    }

    public void remove(Animal animal){
        zoo.getAnimals().remove(animal);
    }

    public Animal findByName(String name){
        for(Animal animal: zoo.getAnimals()){
            if(name.equals(animal.getName()))
                return animal;
        }

        return null;
    }

    private Animal findByChipId(long chipId){
        for(Animal animal: zoo.getAnimals()){
            if(chipId == animal.getChipId())
                return animal;
        }

        return null;
    }
}
