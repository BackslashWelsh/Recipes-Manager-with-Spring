package recipes.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import recipes.service.RecipeService;
import recipes.service.UserDetailsImpl;

@Component("recipeOwnership")
public class RecipeOwnershipEvaluator {

    @Autowired
    private RecipeService recipeService;

    public boolean isOwnedByUser(int id, UserDetailsImpl userDetails) {

       return recipeService.isRecipeIdOwnedByUser(id, userDetails.getUsername());
    }
}
