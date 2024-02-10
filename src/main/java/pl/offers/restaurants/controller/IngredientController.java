package pl.offers.restaurants.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.offers.restaurants.dto.IngredientDTO;
import pl.offers.restaurants.service.IngredientService;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/ingredients", produces = MediaType.APPLICATION_JSON_VALUE)
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping
    @JsonView(IngredientListListView.class)
    public List<IngredientDTO> get() {
        return ingredientService.getAll();
    }

    @GetMapping("/{uuid}")
    @JsonView(IngredientView.class)
    public IngredientDTO get(@PathVariable UUID uuid) {
        return ingredientService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PutMapping("/{uuid}")
    public void put(@PathVariable UUID uuid, @RequestBody @Valid IngredientDTO json) {
        ingredientService.put(uuid, json);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        ingredientService.delete(uuid);
    }

    interface IngredientListListView extends IngredientDTO.View.Basic {
    }

    interface IngredientView extends IngredientListListView {
    }

}
