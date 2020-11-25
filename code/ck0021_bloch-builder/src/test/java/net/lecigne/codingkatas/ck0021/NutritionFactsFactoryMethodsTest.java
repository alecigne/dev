package net.lecigne.codingkatas.ck0021;

@SuppressWarnings("squid:S2187")
public class NutritionFactsFactoryMethodsTest implements NutritionFactsTest<NutritionFactsFactoryMethods> {

    @Override
    public NutritionFactsFactoryMethods getInstance() {
        return NutritionFactsFactoryMethods.withCarbohydrates(servingSize, servings, carbohydrates);
    }

}
