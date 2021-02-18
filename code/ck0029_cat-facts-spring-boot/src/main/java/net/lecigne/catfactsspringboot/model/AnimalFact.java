package net.lecigne.catfactsspringboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AnimalFact {

    private String text;

    @JsonProperty("type")
    private String animalType;

    @Override
    public String toString() {
        return "AnimalFact [text=" + text + ", animalType=" + animalType + "]";
    }

}
