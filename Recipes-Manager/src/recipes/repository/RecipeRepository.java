package recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import recipes.entity.Recipe;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    @Transactional(readOnly = true)
    List<Recipe> findAllByNameContainingIgnoreCaseOrderByDateDesc(String name);

    @Transactional(readOnly = true)
    List<Recipe> findAllByCategoryIgnoreCaseOrderByDateDesc(String category);

    @Transactional(readOnly = true)
    @Query("SELECT  CASE  WHEN count(r)> 0 THEN true ELSE false END " +
            "FROM Recipe r WHERE r.id = :id and r.user.email = :userEmail")
    boolean isRecipeIdOwnedByUser(@Param("id") int id, @Param("userEmail") String userEmail);
}
