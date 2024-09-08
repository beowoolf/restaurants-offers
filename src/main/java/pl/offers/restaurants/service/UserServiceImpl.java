package pl.offers.restaurants.service;

import com.google.common.base.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.offers.restaurants.dto.UserDTO;
import pl.offers.restaurants.model.User;
import pl.offers.restaurants.repo.UserRepo;
import pl.offers.restaurants.utils.ConverterUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static pl.offers.restaurants.utils.ConverterUtils.convert;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public List<UserDTO> getAll() {
        return userRepo.findAll().stream()
                .map(ConverterUtils::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void put(UUID uuid, UserDTO userDTO) {
        if (!Objects.equal(userDTO.getUuid(), uuid))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        User user = userRepo.findByUuid(userDTO.getUuid())
                .orElseGet(() -> newUser(uuid));

        user.setPersonalData(convert(userDTO.getPersonalData()));
        user.setLogginData(convert(userDTO.getLogginData()));
        user.setArchive(userDTO.getArchive());

        if (user.getId() == null)
            userRepo.save(user);
    }

    @Override
    public void delete(UUID uuid) {
        User user = userRepo.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        userRepo.delete(user);
    }

    @Override
    public Optional<UserDTO> getByUuid(UUID uuid) {
        return userRepo.findByUuid(uuid).map(ConverterUtils::convert);
    }

    @Override
    public void validateNewOperation(UUID uuid, UserDTO userDTO) {
        if (!Objects.equal(userDTO.getUuid(), uuid))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        userRepo.findByUuid(userDTO.getUuid())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private User newUser(UUID uuid) {
        return User.builder()
                .withUuid(uuid)
                .build();
    }

}
