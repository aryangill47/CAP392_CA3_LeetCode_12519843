import java.util.*;

class FoodRatings {

    HashMap<String, String> foodCuisine;
    HashMap<String, Integer> foodRating;
    HashMap<String, TreeSet<Food>> cuisineFoods;

    class Food {
        String name;
        int rating;

        Food(String name, int rating) {
            this.name = name;
            this.rating = rating;
        }
    }

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {

        foodCuisine = new HashMap<>();
        foodRating = new HashMap<>();
        cuisineFoods = new HashMap<>();

        for(int i = 0; i < foods.length; i++) {

            foodCuisine.put(foods[i], cuisines[i]);
            foodRating.put(foods[i], ratings[i]);

            cuisineFoods.putIfAbsent(cuisines[i],
                new TreeSet<>((a, b) -> {

                    if(a.rating != b.rating) {
                        return Integer.compare(b.rating, a.rating);
                    }

                    return a.name.compareTo(b.name);
                })
            );

            cuisineFoods.get(cuisines[i]).add(
                new Food(foods[i], ratings[i])
            );
        }
    }

    public void changeRating(String food, int newRating) {

        String cuisine = foodCuisine.get(food);
        int oldRating = foodRating.get(food);

        TreeSet<Food> set = cuisineFoods.get(cuisine);

        set.remove(new Food(food, oldRating));

        foodRating.put(food, newRating);

        set.add(new Food(food, newRating));
    }

    public String highestRated(String cuisine) {

        return cuisineFoods.get(cuisine).first().name;
    }
}