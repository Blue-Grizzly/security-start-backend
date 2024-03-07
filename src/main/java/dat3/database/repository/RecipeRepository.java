package dat3.database.repository;

import dat3.database.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository  extends JpaRepository<Recipe,Integer> {
    List<Recipe> findByCategoryName(String categoryName);
}
