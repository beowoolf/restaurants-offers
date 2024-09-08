package pl.offers.restaurants.service;

import pl.offers.restaurants.dto.IngredientDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IngredientService {

    List<IngredientDTO> getAll();

    void put(UUID uuid, IngredientDTO ingredientDTO);

    void delete(UUID uuid);

    Optional<IngredientDTO> getByUuid(UUID uuid);

}
