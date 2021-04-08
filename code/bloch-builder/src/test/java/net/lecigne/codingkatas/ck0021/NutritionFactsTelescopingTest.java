package net.lecigne.codingkatas.ck0021;

@SuppressWarnings("squid:S2187")
public class NutritionFactsTelescopingTest implements NutritionFactsTest<NutritionFactsTelescoping> {

    @Override
    public NutritionFactsTelescoping getInstance() {
        return new NutritionFactsTelescoping(servingSize, servings, 0, 0, 0, carbohydrates);
    }

}
