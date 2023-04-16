package pl.offers.restaurants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.offers.restaurants.model.Restaurant;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findByUuid(UUID uuid);

}
