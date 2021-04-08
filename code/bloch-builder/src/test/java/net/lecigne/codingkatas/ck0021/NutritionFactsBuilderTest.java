package net.lecigne.codingkatas.ck0021;

class NutritionFactsBuilderTest implements NutritionFactsTest<NutritionFactsBuilder> {

    @Override
    public NutritionFactsBuilder getInstance() {
        return new NutritionFactsBuilder.Builder(servingSize, servings)
                .carbohydrates(carbohydrates)
                .build();
    }
}
