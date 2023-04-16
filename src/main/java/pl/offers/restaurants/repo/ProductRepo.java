package pl.offers.restaurants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.offers.restaurants.model.Product;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> findByUuid(UUID uuid);

}
