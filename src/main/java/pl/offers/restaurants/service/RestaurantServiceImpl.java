package pl.offers.restaurants.service;

import com.google.common.base.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.offers.restaurants.dto.RestaurantDTO;
import pl.offers.restaurants.model.Restaurant;
import pl.offers.restaurants.repo.RestaurantRepo;
import pl.offers.restaurants.utils.ConverterUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static pl.offers.restaurants.utils.ConverterUtils.*;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepo restaurantRepo;

    @Override
    public List<RestaurantDTO> getAll() {
        return restaurantRepo.findAll().stream()
                .map(ConverterUtils::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void put(UUID uuid, RestaurantDTO restaurantDTO) {

        if (!Objects.equal(restaurantDTO.getUuid(), uuid))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Restaurant restaurant = restaurantRepo.findByUuid(restaurantDTO.getUuid())
                .orElseGet(() -> newRestaurant(uuid));

        restaurant.setName(restaurantDTO.getName());
        restaurant.setLogginData(convert(restaurantDTO.getLogginData()));
        restaurant.setCompanyData(convert(restaurantDTO.getCompanyData()));
        restaurant.setOpenTimes(convertOpenTimesFromDTOS(restaurantDTO.getOpenTimes()));
        restaurant.setMenu(convertMenuItemsFromDTOS(restaurantDTO.getMenuItems()));
        restaurant.setDiscountCodes(convertDiscountCodesFromDTOS(restaurantDTO.getDiscountCodes()));
        restaurant.setArchive(restaurantDTO.getArchive());

        if (restaurant.getId() == null)
            restaurantRepo.save(restaurant);
    }

    @Override
    public void delete(UUID uuid) {
        Restaurant restaurant = restaurantRepo.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        restaurantRepo.delete(restaurant);
    }

    @Override
    public Optional<RestaurantDTO> getByUuid(UUID uuid) {
        return restaurantRepo.findByUuid(uuid).map(ConverterUtils::convert);
    }

    private Restaurant newRestaurant(UUID uuid) {
        return Restaurant.builder()
                .withUuid(uuid)
                .build();
    }

}
