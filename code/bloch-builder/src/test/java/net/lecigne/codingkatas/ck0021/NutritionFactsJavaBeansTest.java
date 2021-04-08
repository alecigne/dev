package net.lecigne.codingkatas.ck0021;

@SuppressWarnings("squid:S2187")
public class NutritionFactsJavaBeansTest implements NutritionFactsTest<NutritionFactsJavaBeans> {

    @Override
    public NutritionFactsJavaBeans getInstance() {
        NutritionFactsJavaBeans nf = new NutritionFactsJavaBeans();
        nf.setServingSize(servingSize);
        nf.setServings(servings);
        nf.setCarbohydrates(carbohydrates);
        return nf;
    }

}
