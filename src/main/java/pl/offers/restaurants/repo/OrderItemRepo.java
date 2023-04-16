package pl.offers.restaurants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.offers.restaurants.model.OrderItem;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {

    Optional<OrderItem> findByUuid(UUID uuid);

}
