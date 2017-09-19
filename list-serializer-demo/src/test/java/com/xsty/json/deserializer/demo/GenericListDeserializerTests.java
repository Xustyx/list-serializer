package com.xsty.json.deserializer.demo;

import com.xsty.json.deserializer.demo.model.*;
import com.xsty.json.deserializer.demo.service.ZooService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by XST on 17/09/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ZooService.class})
@JsonTest
public class GenericListDeserializerTests {

    @Autowired
    private JacksonTester<Zoo> json;

    @Autowired
    private ZooService zooService;
    
    String zooJson = "{\"city\":\"Zooland\",\"phoneNumber\":\"987-654-321\",\"animals\":[{\"name\":\"Manny\",\"tuskSize\":23,\"__metadata\":{\"#type\":\"Elephant\",\"#id\":1}},{\"name\":\"Ellie\",\"tuskSize\":18,\"__metadata\":{\"#type\":\"Elephant\",\"#id\":2}},{\"name\":\"Simba\",\"maneColor\":\"Brown\",\"__metadata\":{\"#type\":\"Lion\",\"#id\":3}},{\"name\":\"Scar\",\"maneColor\":\"Black\",\"__metadata\":{\"#type\":\"Lion\",\"#id\":4}},{\"name\":\"Shere Khan\",\"stripeNumber\":54,\"__metadata\":{\"#type\":\"Tiger\",\"#id\":5}},{\"name\":\"Rajah\",\"stripeNumber\":45,\"__metadata\":{\"#type\":\"Tiger\",\"#id\":6}}]}";
    
    
    @Test
    public void testSerialize() throws Exception {
        Zoo zoo = zooService.getZoo();
        Zoo zooWrite = json.write(zoo).getJson();
        isEqualTo(zooJson, zooWrite);
    }

    @Test
    public void testDeserialize() throws IOException {
        Zoo zooParsed = json.parseObject(zooJson);
        Zoo zoo = zooService.getZoo();
        isEqualTo(zooParsed.getCity(),zoo.getCity());
        isEqualTo(zooParsed.getPhoneNumber(),zoo.getPhoneNumber());

        for(int i = 0; i < zooParsed.getAnimals().size(); i++) {
            Animal animalParsed = zooParsed.getAnimals().get(i);
            Animal animal = zoo.getAnimals().get(i);

            isEqualTo(animalParsed.getChipId(),animal.getChipId());
            isEqualTo(animalParsed.classIdentifier(),animal.classIdentifier());
            isEqualTo(animalParsed.getName(),animal.getName());

            if(i < 2){
                isInstanceOf(animalParsed,Elephant.class);
                isInstanceOf(animal,Elephant.class);

                Elephant elephantParsed = (Elephant)animalParsed;
                Elephant elephant = (Elephant)animal;

                isEqualTo(elephantParsed.getTuskSize(),elephant.getTuskSize());
            } else if (i >= 2 && i < 4) {
                isInstanceOf(animalParsed,Lion.class);
                isInstanceOf(animal,Lion.class);

                Lion lionParsed = (Lion)animalParsed;
                Lion lion = (Lion)animal;

                isEqualTo(lionParsed.getManeColor(),lion.getManeColor());
            } else {
                isInstanceOf(animalParsed,Tiger.class);
                isInstanceOf(animal,Tiger.class);

                Tiger tigerParsed = (Tiger)animalParsed;
                Tiger tiger = (Tiger)animal;

                isEqualTo(tigerParsed.getStripeNumber(),tiger.getStripeNumber());
            }
        }
    }

    private void isEqualTo(Object o1, Object o2){
        System.out.println("[" + o1 + "] == [" + o2 + "]");
        assertThat(o1).isEqualTo(o2);
    }

    private void isInstanceOf(Object o1, Class<? extends Animal> clazz){
        System.out.println("[" + o1.getClass() + "] == [" + clazz + "]");
        assertThat(o1).isInstanceOf(clazz);
    }
}
