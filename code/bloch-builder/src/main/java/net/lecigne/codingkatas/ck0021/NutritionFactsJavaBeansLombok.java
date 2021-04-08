package net.lecigne.codingkatas.ck0021;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NutritionFactsJavaBeansLombok implements NutritionFacts {
    private int servingSize; // (mL), required
    private int servings; // required
    private int calories; // optional
    private int fat; // (g), optional
    private int sodium; // (mg), optional
    private int carbohydrates; // (g), optional
}
