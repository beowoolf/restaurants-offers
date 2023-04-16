package pl.offers.restaurants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.offers.restaurants.model.OpenTime;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OpenTimeRepo extends JpaRepository<OpenTime, Long> {

    Optional<OpenTime> findByUuid(UUID uuid);

}
