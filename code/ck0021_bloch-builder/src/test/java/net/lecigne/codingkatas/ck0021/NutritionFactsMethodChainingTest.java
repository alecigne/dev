package net.lecigne.codingkatas.ck0021;

class NutritionFactsMethodChainingTest implements NutritionFactsTest<NutritionFactsMethodChaining> {

    @Override
    public NutritionFactsMethodChaining getInstance() {
        return new NutritionFactsMethodChaining(servingSize, servings).withCarbohydrate(carbohydrates);
    }
}
