package pl.offers.restaurants.service;

import com.google.common.base.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.offers.restaurants.dto.EmployeeDTO;
import pl.offers.restaurants.model.Employee;
import pl.offers.restaurants.repo.EmployeeRepo;
import pl.offers.restaurants.utils.ConverterUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static pl.offers.restaurants.utils.ConverterUtils.convert;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Override
    public List<EmployeeDTO> getAll() {
        return employeeRepo.findAll().stream()
                .map(ConverterUtils::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void put(UUID uuid, EmployeeDTO employeeDTO) {
        if (!Objects.equal(employeeDTO.getUuid(), uuid))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        Employee employee = employeeRepo.findByUuid(employeeDTO.getUuid())
                .orElseGet(() -> newEmployee(uuid));

        employee.setPersonalData(convert(employeeDTO.getPersonalData()));
        employee.setLogginData(convert(employeeDTO.getLogginData()));
        employee.setArchive(employeeDTO.getArchive());

        if (employee.getId() == null)
            employeeRepo.save(employee);
    }

    @Override
    public void delete(UUID uuid) {
        Employee employee = employeeRepo.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        employeeRepo.delete(employee);
    }

    @Override
    public Optional<EmployeeDTO> getByUuid(UUID uuid) {
        return employeeRepo.findByUuid(uuid).map(ConverterUtils::convert);
    }

    private Employee newEmployee(UUID uuid) {
        return Employee.baseBuilder()
                .withUuid(uuid)
                .build();
    }

}
