package pl.offers.restaurants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.offers.restaurants.model.Ingredient;

import java.util.Optional;
import java.util.UUID;

public interface IngredientRepo extends JpaRepository<Ingredient, Long> {

    Optional<Ingredient> findByUuid(UUID uuid);

}
