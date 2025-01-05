package pl.offers.restaurants.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.offers.restaurants.dto.EmployeeDTO;
import pl.offers.restaurants.dto.LogginDataDTO;
import pl.offers.restaurants.dto.PersonalDataDTO;
import pl.offers.restaurants.service.EmployeeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    @JsonView(EmployeeListListView.class)
    public List<EmployeeDTO> get() {
        return employeeService.getAll();
    }

    @GetMapping("/{uuid}")
    @JsonView(EmployeeView.class)
    public EmployeeDTO get(@PathVariable UUID uuid) {
        return employeeService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PutMapping("/{uuid}")
    public void put(@PathVariable UUID uuid, @RequestBody @Valid EmployeeDTO json) {
        employeeService.put(uuid, json);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        employeeService.delete(uuid);
    }

    interface EmployeeListListView extends EmployeeDTO.View.Basic, PersonalDataDTO.View.Basic {
    }

    interface EmployeeView extends EmployeeDTO.View.Extended, PersonalDataDTO.View.Extended, LogginDataDTO.View.Basic {
    }

}
