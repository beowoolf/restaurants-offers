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
import pl.offers.restaurants.model.Product;
import pl.offers.restaurants.repo.DishRepo;
import pl.offers.restaurants.repo.MenuItemRepo;
import pl.offers.restaurants.repo.ProductRepo;
import pl.offers.restaurants.utils.ConverterUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepo dishRepo;
    private final MenuItemRepo menuItemRepo;
    private final ProductRepo productRepo;

    @Override
    public List<DishDTO> getAll() {
        return dishRepo.findAll().stream()
                .map(ConverterUtils::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void put(UUID uuid, DishDTO dishDTO) {
        if (!Objects.equal(dishDTO.getUuid(), uuid))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        Product product = productRepo.findByUuid(dishDTO.getProduct().getUuid())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<MenuItem> menuItems = new ArrayList<>();
        if (dishDTO.getMenuItems() != null)
            for (MenuItemDTO d : dishDTO.getMenuItems()) {
                MenuItem menuItem = menuItemRepo.findByUuid(d.getUuid())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
                menuItems.add(menuItem);
            }

        Dish dish = dishRepo.findByUuid(dishDTO.getUuid())
                .orElseGet(() -> newDish(uuid));

        dish.setQuantity(dishDTO.getQuantity());
        dish.setProduct(product);
        dish.setMenuItems(menuItems);

        if (dish.getId() == null)
            dishRepo.save(dish);
    }

    @Override
    public void delete(UUID uuid) {
        Dish dish = dishRepo.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        dishRepo.delete(dish);
    }

    @Override
    public Optional<DishDTO> getByUuid(UUID uuid) {
        return dishRepo.findByUuid(uuid).map(ConverterUtils::convert);
    }

    private Dish newDish(UUID uuid) {
        return Dish.builder()
                .withUuid(uuid)
                .build();
    }

}
