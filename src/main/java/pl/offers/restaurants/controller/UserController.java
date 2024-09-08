package pl.offers.restaurants.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.offers.restaurants.dto.*;
import pl.offers.restaurants.event.OperationEvidenceCreator;
import pl.offers.restaurants.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @GetMapping
    @JsonView(UserListListView.class)
    public List<UserDTO> get() {
        return userService.getAll();
    }

    @GetMapping("/{uuid}")
    @JsonView(UserView.class)
    public UserDTO get(@PathVariable UUID uuid) {
        return userService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PutMapping("/{uuid}")
    @Validated(DataUpdateValidation.class)
    public void put(@PathVariable UUID uuid, @RequestBody @Valid UserDTO json) {
        userService.put(uuid, json);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        userService.delete(uuid);
    }

    @Transactional
    @PostMapping("/{uuid}/new-operation")
    @Validated(NewOperationValidation.class)
    public void postOperation(@PathVariable UUID uuid, @RequestBody @Valid UserDTO json) {
        userService.validateNewOperation(uuid, json);

        OperationEvidenceCreator operationEvidenceCreator = new OperationEvidenceCreator(this, json);
        applicationEventPublisher.publishEvent(operationEvidenceCreator);
    }

    @JsonView(UserView.class)
    @GetMapping("/{uuid}/delivery-addresses")
    public List<DeliveryAddressDTO> getUserAddresses(@PathVariable UUID uuid) {
        UserDTO userDTO = userService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return userDTO.getDeliveryAddress();
    }

    interface UserListListView extends UserDTO.View.Basic, PersonalDataDTO.View.Basic {
    }

    interface UserView extends UserDTO.View.Extended, PersonalDataDTO.View.Extended, LogginDataDTO.View.Basic,
            DeliveryAddressDTO.View.Basic, OperationEvidenceDTO.View.Extended, DiscountCodeDTO.View.Extended {
    }

    interface DataUpdateValidation extends Default, UserDTO.DataUpdateValidation {
    }

    interface NewOperationValidation extends Default, UserDTO.NewOperationValidation {
    }

}
