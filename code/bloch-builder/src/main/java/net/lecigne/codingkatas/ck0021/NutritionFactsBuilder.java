package net.lecigne.codingkatas.ck0021;

public class NutritionFactsBuilder implements NutritionFacts {

    private final int servingSize; // mL
    private final int servings; // per container
    private final int calories;
    private final int fat; // g
    private final int sodium; // mg
    private final int carbohydrates; // g

    private NutritionFactsBuilder(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrates = builder.carbohydrates;
    }

    public static class Builder {

        // Required parameters
        private final int servingSize;
        private final int servings;

        // Optional parameters - initialized to default values
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrates = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int calories) {
            this.calories = calories;
            return this;
        }

        public Builder fat(int fat) {
            this.fat = fat;
            return this;
        }

        public Builder sodium(int sodium) {
            this.sodium = sodium;
            return this;
        }

        public Builder carbohydrates(int carbohydrates) {
            this.carbohydrates = carbohydrates;
            return this;
        }

        public NutritionFactsBuilder build() {
            return new NutritionFactsBuilder(this);
        }
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

    public int getCarbohydrates() {
        return carbohydrates;
    }
}
