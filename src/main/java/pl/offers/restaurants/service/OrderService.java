package pl.offers.restaurants.service;

import pl.offers.restaurants.dto.OrderDTO;
import pl.offers.restaurants.dto.OrderStatusDTO;
import pl.offers.restaurants.dto.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {

    List<OrderDTO> getAll();

    void put(UUID uuid, OrderDTO orderDTO);

    void delete(UUID uuid);

    Optional<OrderDTO> getByUuid(UUID uuid);

    void setIsPaid(OrderDTO orderDTO);

    void setIsGivedOut(UUID uuid, OrderStatusDTO orderStatusDTO);

    void setIsDelivered(UUID uuid, OrderStatusDTO orderStatusDTO);

    UserDTO newOperationForPaidOrder(OrderDTO orderDTO);

}
