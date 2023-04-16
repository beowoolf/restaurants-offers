package pl.offers.restaurants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.offers.restaurants.model.DiscountCode;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DiscountCodeRepo extends JpaRepository<DiscountCode, Long> {

    Optional<DiscountCode> findByUuid(UUID uuid);

}
