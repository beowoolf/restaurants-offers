package pl.offers.restaurants.service;

import com.google.common.base.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.offers.restaurants.dto.DelivererDTO;
import pl.offers.restaurants.dto.OrderDTO;
import pl.offers.restaurants.model.Deliverer;
import pl.offers.restaurants.model.Order;
import pl.offers.restaurants.repo.DelivererRepo;
import pl.offers.restaurants.repo.OrderRepo;
import pl.offers.restaurants.utils.ConverterUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static pl.offers.restaurants.utils.ConverterUtils.convert;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "deliverers")
public class DelivererServiceImpl implements DelivererService {

    private final DelivererRepo delivererRepo;
    private final OrderRepo orderRepo;

    public static Deliverer newDeliverer(UUID uuid) {
        Deliverer deliverer = new Deliverer();
        deliverer.setUuid(uuid);
        return deliverer;
    }

    @Override
    @Cacheable(cacheNames = "deliverers")
    public List<DelivererDTO> getAll() {
        try {
            Thread.sleep(1000L); // to simulate loading
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return delivererRepo.findAll().stream()
                .map(ConverterUtils::convert)
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(cacheNames = "deliverers", allEntries = true)
    public void put(UUID uuid, DelivererDTO delivererDTO) {
        if (!Objects.equal(delivererDTO.getUuid(), uuid))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        Deliverer deliverer = delivererRepo.findByUuid(delivererDTO.getUuid())
                .orElseGet(() -> newDeliverer(delivererDTO.getUuid()));

        List<Order> orders = new ArrayList<>();
        if (delivererDTO.getOrders() != null)
            for (OrderDTO o : delivererDTO.getOrders()) {
                Order order = orderRepo.findByUuid(o.getUuid())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
                orders.add(order);
            }

        deliverer.setPersonalData(convert(delivererDTO.getPersonalData()));
        deliverer.setLogginData(convert(delivererDTO.getLogginData()));
        deliverer.setArchive(delivererDTO.getArchive());
        deliverer.setOrders(orders);

        if (deliverer.getId() == null)
            delivererRepo.save(deliverer);
    }

    @Override
    @CacheEvict(cacheNames = "deliverers", allEntries = true)
    public void delete(UUID uuid) {
        Deliverer deliverer = delivererRepo.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        delivererRepo.delete(deliverer);
    }

    @Override
    public Optional<DelivererDTO> getByUuid(UUID uuid) {
        return delivererRepo.findByUuid(uuid).map(ConverterUtils::convert);
    }

}
