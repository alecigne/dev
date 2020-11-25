package net.lecigne.codingkatas.ck0021;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NutritionFactsBuilderLombok implements NutritionFacts {
    private final int servingSize; // mL
    private final int servings; // per container
    private final int calories;
    private final int fat; // g
    private final int sodium; // mg
    private final int carbohydrates; // g
}
