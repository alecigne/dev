package net.lecigne.codingkatas.ck0021;

@SuppressWarnings("unused")
public class NutritionFactsTelescoping implements NutritionFacts {

    private final int servingSize; // (mL), required
    private final int servings; // required
    private final int calories; // optional
    private final int fat; // (g), optional
    private final int sodium; // (mg), optional
    private final int carbohydrates; // (g), optional

    public NutritionFactsTelescoping(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    public NutritionFactsTelescoping(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }

    public NutritionFactsTelescoping(int servingSize, int servings, int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    public NutritionFactsTelescoping(int servingSize, int servings, int calories, int fat, int sodium) {
        this(servingSize, servings, calories, fat, sodium, 0);
    }

    public NutritionFactsTelescoping(int servingSize, int servings, int calories, int fat, int sodium,
                                     int carbohydrates) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrates = carbohydrates;
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
