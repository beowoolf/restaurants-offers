package pl.offers.restaurants.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.offers.restaurants.dto.DelivererDTO;
import pl.offers.restaurants.dto.LogginDataDTO;
import pl.offers.restaurants.dto.OrderDTO;
import pl.offers.restaurants.dto.PersonalDataDTO;
import pl.offers.restaurants.service.DelivererService;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/deliverers", produces = MediaType.APPLICATION_JSON_VALUE)
public class DelivererController {

    private final DelivererService delivererService;

    @GetMapping
    @JsonView(DelivererListView.class)
    public List<DelivererDTO> get() {
        return delivererService.getAll();
    }

    @GetMapping("/{uuid}")
    @JsonView(DelivererView.class)
    public DelivererDTO get(@PathVariable UUID uuid) {
        return delivererService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PutMapping("/{uuid}")
    @Validated(NewDelivererValidation.class)
    public void put(@PathVariable UUID uuid, @RequestBody @Valid DelivererDTO json) {
        delivererService.put(uuid, json);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        delivererService.delete(uuid);
    }

    interface DelivererListView extends DelivererDTO.View.Basic, PersonalDataDTO.View.Basic {
    }

    interface DelivererView extends DelivererDTO.View.Extended, PersonalDataDTO.View.Extended, LogginDataDTO.View.Basic, OrderDTO.View.Extended {
    }

    interface NewDelivererValidation extends Default, DelivererDTO.NewDelivererValidation {
    }

}
