package pl.offers.restaurants.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.offers.restaurants.dto.CompanyDataDTO;
import pl.offers.restaurants.dto.LogginDataDTO;
import pl.offers.restaurants.dto.OpenTimeDTO;
import pl.offers.restaurants.dto.RestaurantDTO;
import pl.offers.restaurants.service.RestaurantService;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    @JsonView(RestaurantListListView.class)
    public List<RestaurantDTO> get() {
        return restaurantService.getAll();
    }

    @GetMapping("/{uuid}")
    @JsonView(RestaurantView.class)
    public RestaurantDTO get(@PathVariable UUID uuid) {
        return restaurantService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PutMapping("/{uuid}")
    @Validated(DataUpdateValidation.class)
    public void put(@PathVariable UUID uuid, @RequestBody @Valid RestaurantDTO json) {
        restaurantService.put(uuid, json);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        restaurantService.delete(uuid);
    }

    interface RestaurantListListView extends RestaurantDTO.View.Basic {
    }

    interface RestaurantView extends RestaurantDTO.View.Extended, LogginDataDTO.View.Basic, CompanyDataDTO.View.Extended, OpenTimeDTO.View.Extended {
    }

    interface DataUpdateValidation extends Default, RestaurantDTO.DataUpdateValidation {
    }

}
