package pl.offers.restaurants.service;

import pl.offers.restaurants.dto.DiscountCodeDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DiscountCodeService {

    List<DiscountCodeDTO> getAll();

    void put(UUID uuid, DiscountCodeDTO discountCodeDTO);

    void delete(UUID uuid);

    Optional<DiscountCodeDTO> getByUuid(UUID uuid);

}
