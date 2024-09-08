package pl.offers.restaurants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.offers.restaurants.model.OperationEvidence;
import pl.offers.restaurants.model.User;

import java.math.BigDecimal;

public interface OperationEvidenceRepo extends JpaRepository<OperationEvidence, Long> {

    @Query("SELECT COALESCE(SUM(" +
            "CASE " +
            "WHEN e.type = pl.offers.restaurants.model.enums.EvidenceType.DEPOSIT THEN e.amount " +
            "WHEN e.type = pl.offers.restaurants.model.enums.EvidenceType.WITHDRAW " +
            "or e.type = pl.offers.restaurants.model.enums.EvidenceType.PAYMENT THEN -e.amount " +
            "ELSE 0 " +
            "END" +
            "), 0) from OperationEvidence e where e.user = :user")
    BigDecimal getUserAccountBalance(@Param("user") User user);

}
