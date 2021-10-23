package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.entity.Recipe;
import recipes.repository.RecipeRepository;
import recipes.service.RecipeService;
import recipes.service.UserDetailsImpl;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recipe")
@Validated
public class RecipesController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/{id}")
    public Recipe getRecipeById(@Min(1) @PathVariable int id) {
        return recipeService.findRecipeById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public List<Recipe> searchByNameOrCategory(@RequestParam Map<String, String> allRequest) {
        if (allRequest.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        if (allRequest.containsKey("name"))
            return recipeService.findAllRecipeByName(allRequest.get("name"));
        else if (allRequest.containsKey("category"))
            return recipeService.findAllRecipeByCategory(allRequest.get("category"));
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/new")
    public Map<String, Integer> postRecipe(
            @RequestBody @Valid Recipe recipe,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        recipe.setUser(userDetails.getUser());
        int id = recipeService.saveRecipe(recipe);
        return Map.of("id", id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("@recipeOwnership.isOwnedByUser(#id, principal)")
    public void updateRecipe(
            @Min(1) @PathVariable int id, @RequestBody @Valid Recipe updatedRecipe) {
        recipeService.updateRecipeById(id, updatedRecipe);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("@recipeOwnership.isOwnedByUser(#id, principal)")
    public void deleteRecipeById(@Min(1) @PathVariable int id) {
        recipeService.deleteRecipeById(id);
    }

    @DeleteMapping("/delete-all")
    public boolean deleteAllRecipe() {
        return recipeService.deleteAllRecipe();
    }
}