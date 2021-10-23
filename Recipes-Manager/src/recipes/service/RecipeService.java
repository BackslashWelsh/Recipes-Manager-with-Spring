package recipes.service;

import org.springframework.transaction.annotation.Transactional;
import recipes.entity.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {

    int saveRecipe(Recipe recipe);

    Optional<Recipe> findRecipeById(int id);

    List<Recipe> findAllRecipeByName(String name);

    List<Recipe> findAllRecipeByCategory(String category);

    @Transactional
    void deleteRecipeById(int id);

    boolean deleteAllRecipe();

    @Transactional
    void updateRecipeById(int id, Recipe updatedRecipe);

    boolean isRecipeIdOwnedByUser(int id, String email);
}