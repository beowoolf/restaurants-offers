package pl.offers.restaurants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.offers.restaurants.model.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepo extends JpaRepository<Order, Long> {

    Optional<Order> findByUuid(UUID uuid);

}
