package net.lecigne.javaext.feign.catfacts.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class AnimalFact {

  private String text;

  @SerializedName("type")
  private String animalType;

  @Override
  public String toString() {
    return "AnimalFact [text=" + text + ", animalType=" + animalType + "]";
  }

}
