package recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.entity.Recipe;
import recipes.repository.RecipeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    /**
     * @return id of this saved recipe
     */
    @Override
    public int saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe).getId();
    }

    @Override
    public Optional<Recipe> findRecipeById(int id) {
        return recipeRepository.findById(id);
    }

    @Override
    public List<Recipe> findAllRecipeByName(String name) {
        return recipeRepository.findAllByNameContainingIgnoreCaseOrderByDateDesc(name);
    }

    @Override
    public List<Recipe> findAllRecipeByCategory(String category) {
        return recipeRepository.findAllByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    @Override
    public void deleteRecipeById(int id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public boolean deleteAllRecipe() {
        recipeRepository.deleteAll();
        return !recipeRepository.findAll().iterator().hasNext();
    }

    @Override
    public void updateRecipeById(int id, Recipe updatedRecipe) {
        recipeRepository.findById(id)
                .map(recipe -> {
                    recipe.setName(updatedRecipe.getName());
                    recipe.setCategory(updatedRecipe.getCategory());
                    recipe.setDescription(updatedRecipe.getDescription());
                    recipe.setIngredients(updatedRecipe.getIngredients());
                    recipe.setDirections(updatedRecipe.getDirections());
                    return recipeRepository.save(recipe);
                });
    }

    @Override
    public boolean isRecipeIdOwnedByUser(int id, String userEmail) {
        return recipeRepository.isRecipeIdOwnedByUser(id, userEmail);
    }
}