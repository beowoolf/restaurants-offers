package pl.offers.restaurants.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.offers.restaurants.dto.OpenTimeDTO;
import pl.offers.restaurants.dto.RestaurantDTO;
import pl.offers.restaurants.service.OpenTimeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/open-times", produces = MediaType.APPLICATION_JSON_VALUE)
public class OpenTimeController {

    private final OpenTimeService openTimeService;

    @GetMapping
    @JsonView(OpenTimeListListView.class)
    public List<OpenTimeDTO> get() {
        return openTimeService.getAll();
    }

    @PostMapping
    @Transactional
    public void post(@RequestBody List<@Valid OpenTimeDTO> openTimesJson) {
        for (OpenTimeDTO openTimeDTO : openTimesJson)
            put(openTimeDTO.getUuid(), openTimeDTO);
    }

    @GetMapping("/{uuid}")
    @JsonView(OpenTimeView.class)
    public OpenTimeDTO get(@PathVariable UUID uuid) {
        return openTimeService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PutMapping("/{uuid}")
    public void put(@PathVariable UUID uuid, @RequestBody @Valid OpenTimeDTO json) {
        openTimeService.put(uuid, json);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        openTimeService.delete(uuid);
    }

    interface OpenTimeListListView extends OpenTimeDTO.View.Basic {
    }

    interface OpenTimeView extends OpenTimeDTO.View.Extended, RestaurantDTO.View.Id {
    }

}
