package pl.offers.restaurants.service;

import pl.offers.restaurants.model.OperationEvidence;
import pl.offers.restaurants.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface OperationEvidenceService {

    List<OperationEvidence> getAll();

    void add(OperationEvidence operationEvidence);

    void delete(OperationEvidence operationEvidence);

    BigDecimal getUserAccountBalance(User user);

    BigDecimal getAccountBalanceAfterOperation(OperationEvidence operationEvidence);

}
