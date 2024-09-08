package pl.offers.restaurants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.offers.restaurants.model.DiscountCode;

import java.util.Optional;
import java.util.UUID;

public interface DiscountCodeRepo extends JpaRepository<DiscountCode, Long> {

    Optional<DiscountCode> findByUuid(UUID uuid);

}
