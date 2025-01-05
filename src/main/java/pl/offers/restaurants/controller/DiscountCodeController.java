package pl.offers.restaurants.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.offers.restaurants.dto.DiscountCodeDTO;
import pl.offers.restaurants.dto.PeriodDTO;
import pl.offers.restaurants.service.DiscountCodeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/discount-codes", produces = MediaType.APPLICATION_JSON_VALUE)
public class DiscountCodeController {

    private final DiscountCodeService discountCodeService;

    @GetMapping
    @JsonView(DiscountCodeListListView.class)
    public List<DiscountCodeDTO> get() {
        return discountCodeService.getAll();
    }

    @GetMapping("/{uuid}")
    @JsonView(DiscountCodeView.class)
    public DiscountCodeDTO get(@PathVariable UUID uuid) {
        return discountCodeService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PutMapping("/{uuid}")
    public void put(@PathVariable UUID uuid, @RequestBody @Valid DiscountCodeDTO json) {
        discountCodeService.put(uuid, json);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        discountCodeService.delete(uuid);
    }

    interface DiscountCodeListListView extends DiscountCodeDTO.View.Basic, PeriodDTO.View.Basic {
    }

    interface DiscountCodeView extends DiscountCodeDTO.View.Extended, PeriodDTO.View.Basic {
    }

}
