package pl.offers.restaurants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.offers.restaurants.model.Deliverer;

import java.util.Optional;
import java.util.UUID;

public interface DelivererRepo extends JpaRepository<Deliverer, Long> {

    Optional<Deliverer> findByUuid(UUID uuid);

}
