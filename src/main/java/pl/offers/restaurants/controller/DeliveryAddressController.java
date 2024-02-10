package pl.offers.restaurants.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.offers.restaurants.dto.DeliveryAddressDTO;
import pl.offers.restaurants.dto.UserDTO;
import pl.offers.restaurants.service.DeliveryAddressService;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/delivery-address", produces = MediaType.APPLICATION_JSON_VALUE)
public class DeliveryAddressController {

    private final DeliveryAddressService deliveryAddressService;

    @GetMapping
    @JsonView(DeliveryAddressListListView.class)
    public List<DeliveryAddressDTO> get() {
        return deliveryAddressService.getAll();
    }

    @GetMapping("/{uuid}")
    @JsonView(DeliveryAddressView.class)
    public DeliveryAddressDTO get(@PathVariable UUID uuid) {
        return deliveryAddressService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PutMapping("/{uuid}")
    public void put(@PathVariable UUID uuid, @RequestBody @Valid DeliveryAddressDTO json) {
        deliveryAddressService.put(uuid, json);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        deliveryAddressService.delete(uuid);
    }

    interface DeliveryAddressListListView extends DeliveryAddressDTO.View.Basic, UserDTO.View.Id {
    }

    interface DeliveryAddressView extends DeliveryAddressDTO.View.Extended, UserDTO.View.Id {
    }

}
