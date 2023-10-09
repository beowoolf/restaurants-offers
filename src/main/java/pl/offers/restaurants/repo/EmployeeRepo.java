package pl.offers.restaurants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.offers.restaurants.model.Employee;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    Optional<Employee> findByUuid(UUID uuid);

}
