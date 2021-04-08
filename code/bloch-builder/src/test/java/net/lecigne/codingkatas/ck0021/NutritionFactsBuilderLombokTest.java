package net.lecigne.codingkatas.ck0021;

class NutritionFactsBuilderLombokTest implements NutritionFactsTest<NutritionFactsBuilderLombok> {

    @Override
    public NutritionFactsBuilderLombok getInstance() {
        return NutritionFactsBuilderLombok.builder()
                .servingSize(servingSize)
                .servings(servings)
                .carbohydrates(carbohydrates)
                .build();
    }
}
