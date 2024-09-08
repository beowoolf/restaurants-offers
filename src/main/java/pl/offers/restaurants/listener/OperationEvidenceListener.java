package pl.offers.restaurants.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import pl.offers.restaurants.dto.UserDTO;
import pl.offers.restaurants.event.OperationEvidenceCreator;
import pl.offers.restaurants.model.OperationEvidence;
import pl.offers.restaurants.model.User;
import pl.offers.restaurants.repo.UserRepo;
import pl.offers.restaurants.service.OperationEvidenceService;
import pl.offers.restaurants.utils.ConverterUtils;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class OperationEvidenceListener {

    private final OperationEvidenceService operationEvidenceService;
    private final UserRepo userRepo;

    @EventListener
    public void onAddOperation(OperationEvidenceCreator operationEvidenceCreator) {
        UserDTO userDTO = operationEvidenceCreator.getUserDTO();
        OperationEvidence operationEvidence = ConverterUtils.convert(userDTO.getOperationEvidence().stream().findFirst().orElseThrow());
        User user = userRepo.findByUuid(userDTO.getUuid()).orElseThrow();
        operationEvidence.setUser(user);

        validateAccountBalanceAfterOperation(operationEvidence);
        operationEvidenceService.add(operationEvidence);
    }

    private void validateAccountBalanceAfterOperation(OperationEvidence operationEvidence) {
        BigDecimal acconutBalanceAfterOperation = operationEvidenceService.getAccountBalanceAfterOperation(operationEvidence);
        if (acconutBalanceAfterOperation.compareTo(BigDecimal.ZERO) <= 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

}
