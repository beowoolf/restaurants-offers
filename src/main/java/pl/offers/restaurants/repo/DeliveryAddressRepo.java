package pl.offers.restaurants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.offers.restaurants.model.DeliveryAddress;

import java.util.Optional;
import java.util.UUID;

public interface DeliveryAddressRepo extends JpaRepository<DeliveryAddress, Long> {

    Optional<DeliveryAddress> findByUuid(UUID uuid);

}
