package net.lecigne.codingkatas.ck0021;

@SuppressWarnings("unused")
public class NutritionFactsMethodChaining implements NutritionFacts {

    private final int servingSize;
    private final int servings;
    private int calories;
    private int fat;
    private int sodium;
    private int carbohydrates;

    public NutritionFactsMethodChaining(int servingSize, int servings) {
        this.servingSize = servingSize;
        this.servings = servings;
    }

    public NutritionFactsMethodChaining withCalories(int calories) {
        this.calories = calories;
        return this;
    }

    public NutritionFactsMethodChaining withFat(int fat) {
        this.fat = fat;
        return this;
    }

    public NutritionFactsMethodChaining withSodium(int sodium) {
        this.sodium = sodium;
        return this;
    }

    public NutritionFactsMethodChaining withCarbohydrate(int carbohydrate) {
        this.carbohydrates = carbohydrate;
        return this;
    }

    @Override
    public int getServingSize() {
        return servingSize;
    }

    @Override
    public int getServings() {
        return servings;
    }

    @Override
    public int getCalories() {
        return calories;
    }

    @Override
    public int getFat() {
        return fat;
    }

    @Override
    public int getSodium() {
        return sodium;
    }

    @Override
    public int getCarbohydrates() {
        return carbohydrates;
    }
}
