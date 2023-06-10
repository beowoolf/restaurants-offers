package pl.offers.restaurants.service;

import com.google.common.base.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.offers.restaurants.dto.IngredientDTO;
import pl.offers.restaurants.model.Ingredient;
import pl.offers.restaurants.repo.IngredientRepo;
import pl.offers.restaurants.utils.ConverterUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepo ingredientRepo;

    @Override
    public List<IngredientDTO> getAll() {
        return ingredientRepo.findAll().stream()
                .map(ConverterUtils::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void put(UUID uuid, IngredientDTO ingredientDTO) {
        if (!Objects.equal(ingredientDTO.getUuid(), uuid))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Ingredient ingredient = ingredientRepo.findByUuid(ingredientDTO.getUuid())
                .orElseGet(() -> newIngredient(uuid));

        ingredient.setName(ingredientDTO.getName());
        ingredient.setIsAllergen(ingredientDTO.getIsAllergen());

        if (ingredient.getId() == null)
            ingredientRepo.save(ingredient);
    }

    @Override
    public void delete(UUID uuid) {
        Ingredient ingredient = ingredientRepo.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ingredientRepo.delete(ingredient);
    }

    @Override
    public Optional<IngredientDTO> getByUuid(UUID uuid) {
        return ingredientRepo.findByUuid(uuid).map(ConverterUtils::convert);
    }

    private Ingredient newIngredient(UUID uuid) {
        return Ingredient.builder()
                .withUuid(uuid)
                .build();
    }

}
