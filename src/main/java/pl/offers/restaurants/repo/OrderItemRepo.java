package pl.offers.restaurants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.offers.restaurants.model.OrderItem;

import java.util.Optional;
import java.util.UUID;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {

    Optional<OrderItem> findByUuid(UUID uuid);

}
