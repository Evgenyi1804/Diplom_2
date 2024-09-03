package org.example.Moderator;

import org.example.model.ingredient.Ingredient;
import org.example.model.ingredient.AddIngredients;
import java.util.*;

public class IngredientModerator {
    private Random random = new Random();
    private Map<String, String[]> ingredientsMap = new HashMap<>();
    List<Ingredient> addIngredients = new AddIngredients().getIngredients();

    public Map<String, String[]> getCorrectIngredients() {
        String[] ingredients = new String[random.nextInt(addIngredients.size() + 1)];
        for (int i = 0; i < ingredients.length; i++) {
            ingredients[i] = addIngredients.get(random.nextInt(addIngredients.size())).get_id();
        }
        ingredientsMap.put("ingredients", ingredients);
        return ingredientsMap;
    }

    public Map<String, String[]> getEmptyIngredients() {
        return ingredientsMap;
    }

    public Map<String, String[]> getIncorrectIngredients() {
        ingredientsMap.put("ingredients", new String[] {"incorrect hash"});
        return ingredientsMap;
    }
}
