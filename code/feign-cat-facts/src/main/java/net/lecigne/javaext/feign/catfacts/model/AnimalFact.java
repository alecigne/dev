package net.lecigne.javaext.feign.catfacts.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * A fact about an animal.
 *
 * @author Anthony Le Cigne
 * @since 2019-12-05
 */
@Getter
public class AnimalFact {

    /**
     * The fact itself
     */
    private String text;

    /**
     * The kind of animal the fact is about
     */
    @SerializedName("type")
    private String animalType;

    @Override
    public String toString() {
        return "AnimalFact [text=" + text + ", animalType=" + animalType + "]";
    }

}
