package net.lecigne.codingkatas.ck0021;

@SuppressWarnings("squid:S2187")
public class NutritionFactsJavaBeansLombokTest implements NutritionFactsTest<NutritionFactsJavaBeansLombok> {

    @Override
    public NutritionFactsJavaBeansLombok getInstance() {
        NutritionFactsJavaBeansLombok nf = new NutritionFactsJavaBeansLombok();
        nf.setServingSize(servingSize);
        nf.setServings(servings);
        nf.setCarbohydrates(carbohydrates);
        return nf;
    }

}
