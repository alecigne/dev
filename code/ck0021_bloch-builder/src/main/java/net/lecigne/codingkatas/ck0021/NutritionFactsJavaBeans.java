package net.lecigne.codingkatas.ck0021;

@SuppressWarnings("unused")
public class NutritionFactsJavaBeans implements NutritionFacts {

    private int servingSize; // (mL), required
    private int servings; // required
    private int calories; // optional
    private int fat; // (g), optional
    private int sodium; // (mg), optional
    private int carbohydrates; // (g), optional

    public NutritionFactsJavaBeans() {
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

    public void setServingSize(int val) {
        servingSize = val;
    }

    public void setServings(int val) {
        servings = val;
    }

    public void setCalories(int val) {
        calories = val;
    }

    public void setFat(int val) {
        fat = val;
    }

    public void setSodium(int val) {
        sodium = val;
    }

    public void setCarbohydrates(int val) {
        carbohydrates = val;
    }

}
