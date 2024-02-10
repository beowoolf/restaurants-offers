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
import pl.offers.restaurants.service.DelivererService;
import pl.offers.restaurants.service.OrderService;
import pl.offers.restaurants.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    private final OrderService orderService;
    private final DelivererService delivererService;
    private final UserService userService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @GetMapping
    @JsonView(OrderListListView.class)
    public List<OrderDTO> get() {
        return orderService.getAll();
    }

    @GetMapping(params = {"user"})
    @JsonView(OrderListListView.class)
    public List<OrderDTO> getByUser(@RequestParam("user") UUID userUuid) {
        UserDTO user = userService.getByUuid(userUuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return user.getOrders();
    }

    @JsonView(OrderListListView.class)
    @GetMapping(params = {"deliverer"})
    public List<OrderDTO> getByDeliverer(@RequestParam("delivererUuid") UUID delivererUuid) {
        DelivererDTO delivererDTO = delivererService.getByUuid(delivererUuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return delivererDTO.getOrders();
    }

    @GetMapping("/{uuid}")
    @JsonView(OrderView.class)
    public OrderDTO get(@PathVariable UUID uuid) {
        return orderService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PutMapping("/{uuid}")
    @Validated(OrderDataUpdateValidation.class)
    public void put(@PathVariable UUID uuid, @RequestBody @Valid OrderDTO json) {
        orderService.put(uuid, json);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        orderService.delete(uuid);
    }

    @Transactional
    @PatchMapping("/{uuid}/paid")
    @Validated(OrderStatusValidation.class)
    public void patchIsPaid(@PathVariable UUID uuid) {
        OrderDTO orderDTO = orderService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        orderService.setIsPaid(orderDTO);

        OperationEvidenceCreator operationEvidenceCreator = new OperationEvidenceCreator(this, orderService.newOperationForPaidOrder(orderDTO));
        applicationEventPublisher.publishEvent(operationEvidenceCreator);
    }

    @Transactional
    @PatchMapping("/{uuid}/gived-out")
    @Validated(GiveOutValidation.class)
    public void patchIsGivedOut(@PathVariable UUID uuid, @RequestBody @Valid OrderStatusDTO orderStatusJson) {
        orderService.setIsGivedOut(uuid, orderStatusJson);
    }

    @Transactional
    @PatchMapping("/{uuid}/delivered")
    @Validated(DeliveryValidation.class)
    public void patchIsDelivered(@PathVariable UUID uuid, @RequestBody @Valid OrderStatusDTO orderStatusJson) {
        orderService.setIsDelivered(uuid, orderStatusJson);
    }

    interface OrderListListView extends OrderDTO.View.Basic, UserDTO.View.Id, DelivererDTO.View.Id, RestaurantDTO.View.Id {
    }

    interface OrderView extends OrderDTO.View.Extended, UserDTO.View.Id, DelivererDTO.View.Id, RestaurantDTO.View.Id {
    }

    interface OrderDataUpdateValidation extends Default, OrderDTO.OrderValidation {
    }

    interface OrderStatusValidation extends Default, OrderDTO.OrderStatusValidation {
    }

    interface GiveOutValidation extends Default, OrderDTO.OrderStatusValidation, OrderStatusDTO.GiveOutStatusValidation {
    }

    interface DeliveryValidation extends Default, OrderDTO.OrderStatusValidation, OrderStatusDTO.DeliveryValidation {
    }

}
