package net.lecigne.codingkatas.ck0021;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

interface NutritionFactsTest<T extends NutritionFacts> {

    int servingSize = 10;
    int servings = 20;
    int carbohydrates = 54;

    T getInstance();

    @Test
    default void nutritionFacts_shouldWork() {
        NutritionFacts nf = getInstance();
        assertThat(nf.getServingSize()).isEqualTo(servingSize);
        assertThat(nf.getServings()).isEqualTo(servings);
        assertThat(nf.getCalories()).isZero();
        assertThat(nf.getFat()).isZero();
        assertThat(nf.getSodium()).isZero();
        assertThat(nf.getCarbohydrates()).isEqualTo(carbohydrates);
    }

}
