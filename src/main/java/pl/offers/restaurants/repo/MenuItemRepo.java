package pl.offers.restaurants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.offers.restaurants.model.MenuItem;

import java.util.Optional;
import java.util.UUID;

public interface MenuItemRepo extends JpaRepository<MenuItem, Long> {

    Optional<MenuItem> findByUuid(UUID uuid);

}
