package pl.offers.restaurants.service;

import com.google.common.base.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.offers.restaurants.dto.DishDTO;
import pl.offers.restaurants.dto.MenuItemDTO;
import pl.offers.restaurants.model.Dish;
import pl.offers.restaurants.model.MenuItem;
import pl.offers.restaurants.model.Restaurant;
import pl.offers.restaurants.repo.DishRepo;
import pl.offers.restaurants.repo.MenuItemRepo;
import pl.offers.restaurants.repo.RestaurantRepo;
import pl.offers.restaurants.utils.ConverterUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepo menuItemRepo;
    private final RestaurantRepo restaurantRepo;
    private final DishRepo dishRepo;

    @Override
    public List<MenuItemDTO> getAll() {
        return menuItemRepo.findAll().stream()
                .map(ConverterUtils::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void put(UUID uuid, MenuItemDTO menuItemDTO) {
        if (!Objects.equal(menuItemDTO.getUuid(), uuid))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        Restaurant restaurant = restaurantRepo.findByUuid(menuItemDTO.getRestaurant().getUuid())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<Dish> dishes = new ArrayList<>();
        for (DishDTO d : menuItemDTO.getDishes()) {
            Dish dish = dishRepo.findByUuid(d.getUuid())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            dishes.add(dish);
        }

        MenuItem menuItem = menuItemRepo.findByUuid(menuItemDTO.getUuid())
                .orElseGet(() -> newMenuItem(uuid, restaurant));

        menuItem.setName(menuItemDTO.getName());
        menuItem.setNettoPrice(menuItemDTO.getNettoPrice());
        menuItem.setVatTax(menuItemDTO.getVatTax());
        menuItem.setBruttoPrice(menuItemDTO.getBruttoPrice());
        menuItem.setDishes(dishes);

        if (menuItem.getId() == null)
            menuItemRepo.save(menuItem);
    }

    @Override
    public void delete(UUID uuid) {
        MenuItem menuItem = menuItemRepo.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        menuItemRepo.delete(menuItem);
    }

    @Override
    public Optional<MenuItemDTO> getByUuid(UUID uuid) {
        return menuItemRepo.findByUuid(uuid).map(ConverterUtils::convert);
    }

    private MenuItem newMenuItem(UUID uuid, Restaurant restaurant) {
        return MenuItem.builder()
                .withUuid(uuid)
                .withRestaurant(restaurant)
                .build();
    }

}
