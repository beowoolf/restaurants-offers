package pl.offers.restaurants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.offers.restaurants.model.Order;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    Optional<Order> findByUuid(UUID uuid);

}
