package pl.offers.restaurants.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.offers.restaurants.model.OperationEvidence;
import pl.offers.restaurants.model.User;
import pl.offers.restaurants.repo.OperationEvidenceRepo;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationEvidenceServiceImpl implements OperationEvidenceService {

    private final OperationEvidenceRepo operationEvidenceRepo;

    @Override
    public List<OperationEvidence> getAll() {
        return operationEvidenceRepo.findAll();
    }

    @Override
    public void add(OperationEvidence operationEvidence) {
        operationEvidenceRepo.save(operationEvidence);
    }

    @Override
    public void delete(OperationEvidence operationEvidence) {
        operationEvidenceRepo.delete(operationEvidence);
    }

    @Override
    public BigDecimal getUserAccountBalance(User user) {
        return operationEvidenceRepo.getUserAccountBalance(user);
    }

    @Override
    public BigDecimal getAccountBalanceAfterOperation(OperationEvidence operationEvidence) {
        BigDecimal balanceBefore = getUserAccountBalance(operationEvidence.getUser());
        BigDecimal blanceAfter;

        switch (operationEvidence.getType()) {
            case WITHDRAW:
            case PAYMENT:
                blanceAfter = balanceBefore.subtract(operationEvidence.getAmount());
                break;
            case DEPOSIT:
                blanceAfter = balanceBefore.add(operationEvidence.getAmount());
                break;
            default:
                throw new UnsupportedOperationException();
        }
        return blanceAfter;
    }

}
