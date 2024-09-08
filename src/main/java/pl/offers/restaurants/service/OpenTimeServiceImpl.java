package pl.offers.restaurants.service;

import com.google.common.base.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.offers.restaurants.dto.OpenTimeDTO;
import pl.offers.restaurants.model.OpenTime;
import pl.offers.restaurants.model.Restaurant;
import pl.offers.restaurants.repo.OpenTimeRepo;
import pl.offers.restaurants.repo.RestaurantRepo;
import pl.offers.restaurants.utils.ConverterUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static pl.offers.restaurants.utils.ConverterUtils.convert;

@Service
@RequiredArgsConstructor
public class OpenTimeServiceImpl implements OpenTimeService {

    private final OpenTimeRepo openTimeRepo;
    private final RestaurantRepo restaurantRepo;

    @Override
    public List<OpenTimeDTO> getAll() {
        return openTimeRepo.findAll().stream()
                .map(ConverterUtils::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void put(UUID uuid, OpenTimeDTO openTimeDTO) {
        if (!Objects.equal(openTimeDTO.getUuid(), uuid))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        Restaurant restaurant = restaurantRepo.findByUuid(openTimeDTO.getRestaurant().getUuid())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        OpenTime openTime = openTimeRepo.findByUuid(openTimeDTO.getUuid())
                .orElseGet(() -> newOpenTime(uuid, restaurant));

        if (!Objects.equal(openTime.getRestaurant().getUuid(), openTimeDTO.getRestaurant().getUuid()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        openTime.setDayOfWeek(openTimeDTO.getDayOfWeek());
        openTime.setPeriodTime(convert(openTimeDTO.getPeriodTime()));

        if (openTime.getId() == null)
            openTimeRepo.save(openTime);
    }

    @Override
    public void delete(UUID uuid) {
        OpenTime openTime = openTimeRepo.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        openTimeRepo.delete(openTime);
    }

    @Override
    public Optional<OpenTimeDTO> getByUuid(UUID uuid) {
        return openTimeRepo.findByUuid(uuid).map(ConverterUtils::convert);
    }

    private OpenTime newOpenTime(UUID uuid, Restaurant restaurant) {
        return OpenTime.builder()
                .withUuid(uuid)
                .withRestaurant(restaurant)
                .build();
    }

}
