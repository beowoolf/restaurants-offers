package pl.offers.restaurants.service;

import pl.offers.restaurants.exception.UnsupportedDataTypeException;
import pl.offers.restaurants.model.DiscountCode;
import pl.offers.restaurants.model.OrderItem;
import pl.offers.restaurants.model.enums.PriceType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderItemService {

    List<OrderItem> getAll();

    void add(OrderItem orderItem);

    void delete(OrderItem orderItem);

    Optional<OrderItem> getByUuid(UUID uuid);

    BigDecimal calculatePrice(List<OrderItem> orderItemList, BigDecimal startPrice, PriceType priceType) throws UnsupportedDataTypeException;

    BigDecimal applyDiscount(DiscountCode discountCode, BigDecimal orderBruttoPrice) throws UnsupportedDataTypeException;

}
