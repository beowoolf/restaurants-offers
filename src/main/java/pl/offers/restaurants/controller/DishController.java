package pl.offers.restaurants.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.offers.restaurants.dto.DishDTO;
import pl.offers.restaurants.dto.MenuItemDTO;
import pl.offers.restaurants.dto.ProductDTO;
import pl.offers.restaurants.service.DishService;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController {

    private final DishService dishService;

    @GetMapping
    @JsonView(DishListListView.class)
    public List<DishDTO> get() {
        return dishService.getAll();
    }

    @GetMapping("/{uuid}")
    @JsonView(DishView.class)
    public DishDTO get(@PathVariable UUID uuid) {
        return dishService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PutMapping("/{uuid}")
    @Validated(DishDataUpdateValidation.class)
    public void put(@PathVariable UUID uuid, @RequestBody @Valid DishDTO json) {
        dishService.put(uuid, json);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        dishService.delete(uuid);
    }

    interface DishListListView extends DishDTO.View.Basic {
    }

    interface DishView extends DishDTO.View.Extended, ProductDTO.View.Extended, MenuItemDTO.View.Basic {
    }

    interface DishDataUpdateValidation extends Default, DishDTO.DataUpdateValidation {
    }

}
