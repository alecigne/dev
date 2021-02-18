package net.lecigne.codingkatas.springwebmvcresttemplate.animalfact;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnimalFact {

    private String text;

    @JsonProperty("type")
    private String animalType;

    @Override
    public String toString() {
        return "AnimalFact [text=" + text + ", animal type=" + animalType + "]";
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

}
