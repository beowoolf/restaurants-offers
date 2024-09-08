package pl.offers.restaurants.service;

import pl.offers.restaurants.dto.ProductDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    List<ProductDTO> getAll();

    void put(UUID uuid, ProductDTO productDTO);

    void delete(UUID uuid);

    Optional<ProductDTO> getByUuid(UUID uuid);

}
