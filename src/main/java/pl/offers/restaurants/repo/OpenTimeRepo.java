package pl.offers.restaurants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.offers.restaurants.model.OpenTime;

import java.util.Optional;
import java.util.UUID;

public interface OpenTimeRepo extends JpaRepository<OpenTime, Long> {

    Optional<OpenTime> findByUuid(UUID uuid);

}
