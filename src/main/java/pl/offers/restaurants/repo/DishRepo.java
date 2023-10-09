package pl.offers.restaurants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.offers.restaurants.model.Dish;

import java.util.Optional;
import java.util.UUID;

public interface DishRepo extends JpaRepository<Dish, Long> {

    Optional<Dish> findByUuid(UUID uuid);

}
