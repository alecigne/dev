package net.lecigne.codingkatas.ck0021;

@SuppressWarnings("unused")
public class NutritionFactsFactoryMethods implements NutritionFacts {

    private final int servingSize; // (mL), required
    private final int servings; // required
    private final int calories; // optional
    private final int fat; // (g), optional
    private final int sodium; // (mg), optional
    private final int carbohydrates; // (g), optional

    public NutritionFactsFactoryMethods(int servingSize, int servings, int calories, int fat, int sodium,
                                        int carbohydrates) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrates = carbohydrates;
    }

    public static NutritionFactsFactoryMethods withCalories(int servingSize, int servings, int calories) {
        return new NutritionFactsFactoryMethods(servingSize, servings, calories, 0, 0, 0);
    }

    public static NutritionFactsFactoryMethods withFat(int servingSize, int servings, int fat) {
        return new NutritionFactsFactoryMethods(servingSize, servings, 0, fat, 0, 0);
    }

    public static NutritionFactsFactoryMethods withSodium(int servingSize, int servings, int sodium) {
        return new NutritionFactsFactoryMethods(servingSize, servings, 0, 0, sodium, 0);
    }

    public static NutritionFactsFactoryMethods withCarbohydrates(int servingSize, int servings, int carbohydrates) {
        return new NutritionFactsFactoryMethods(servingSize, servings, 0, 0, 0, carbohydrates);
    }

    public static NutritionFactsFactoryMethods withCaloriesAndFat(int servingSize, int servings, int calories,
                                                                  int fat) {
        return new NutritionFactsFactoryMethods(servingSize, servings, calories, fat, 0, 0);
    }

    public static NutritionFactsFactoryMethods withCaloriesAndSodium(int servingSize, int servings, int calories,
                                                                     int sodium) {
        return new NutritionFactsFactoryMethods(servingSize, servings, calories, 0, sodium, 0);
    }

    public static NutritionFactsFactoryMethods withCaloriesAndCarbohydrates(int servingSize, int servings, int calories,
                                                                            int carbohydrates) {
        return new NutritionFactsFactoryMethods(servingSize, servings, calories, 0, 0, carbohydrates);
    }

    public static NutritionFactsFactoryMethods withFatAndSodium(int servingSize, int servings, int fat, int sodium) {
        return new NutritionFactsFactoryMethods(servingSize, servings, 0, fat, sodium, 0);
    }

    public static NutritionFactsFactoryMethods withFatAndCarbohydrates(int servingSize, int servings, int fat,
                                                                       int carbohydrates) {
        return new NutritionFactsFactoryMethods(servingSize, servings, 0, fat, 0, carbohydrates);
    }

    public static NutritionFactsFactoryMethods withSodiumAndCarbohydrates(int servingSize, int servings, int sodium,
                                                                          int carbohydrates) {
        return new NutritionFactsFactoryMethods(servingSize, servings, 0, 0, sodium, carbohydrates);
    }

    // This could go on...

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
