package pl.offers.restaurants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.offers.restaurants.model.Restaurant;

import java.util.Optional;
import java.util.UUID;

public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findByUuid(UUID uuid);

}
