package pl.offers.restaurants.service;

import pl.offers.restaurants.dto.DelivererDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DelivererService {

    List<DelivererDTO> getAll();

    void put(UUID uuid, DelivererDTO delivererDTO);

    void delete(UUID uuid);

    Optional<DelivererDTO> getByUuid(UUID uuid);

}
