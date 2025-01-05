package pl.offers.restaurants.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.offers.restaurants.dto.DishDTO;
import pl.offers.restaurants.dto.MenuItemDTO;
import pl.offers.restaurants.dto.RestaurantDTO;
import pl.offers.restaurants.service.MenuItemService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/menu-items", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuItemController {

    private final MenuItemService menuItemService;

    @GetMapping
    @JsonView(MenuItemListListView.class)
    public List<MenuItemDTO> get() {
        return menuItemService.getAll();
    }

    @GetMapping("/{uuid}")
    @JsonView(MenuItemView.class)
    public MenuItemDTO get(@PathVariable UUID uuid) {
        return menuItemService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PutMapping("/{uuid}")
    public void put(@PathVariable UUID uuid, @RequestBody @Valid MenuItemDTO json) {
        menuItemService.put(uuid, json);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        menuItemService.delete(uuid);
    }

    interface MenuItemListListView extends MenuItemDTO.View.Basic, RestaurantDTO.View.Id {
    }

    interface MenuItemView extends MenuItemDTO.View.Extended, RestaurantDTO.View.Id, DishDTO.View.Id {
    }

}
