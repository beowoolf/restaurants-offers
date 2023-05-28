package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.persistence.Embedded;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder(setterPrefix = "with")
public class OrderDTO {

    @JsonView(View.Basic.class)
    @NotNull
    private UUID uuid;
    @JsonView(View.Extended.class)
    @Digits(integer = 10, fraction = 2)
    @Min(0)
    @Null(groups = OrderValidation.class)
    private BigDecimal nettoPrice;
    @JsonView(View.Extended.class)
    @Digits(integer = 10, fraction = 2)
    @Min(0)
    @NotNull
    private BigDecimal bruttoPrice;
    @JsonView(View.Extended.class)
    @Nullable
    private DiscountCodeDTO discountCode;
    @JsonView(View.Extended.class)
    @Digits(integer = 10, fraction = 2)
    @Min(0)
    @NotNull
    private BigDecimal amountToPayBrutto;
    @JsonView(View.Extended.class)
    @Nullable
    private String note;
    @JsonView(View.Basic.class)
    @Null(groups = OrderValidation.class)
    @NotNull(groups = OrderStatusValidation.class)
    @Embedded
    private OrderStatusDTO orderStatus;
    @JsonView(View.Extended.class)
    @NotNull
    private DeliveryAddressDTO deliveryAddress;
    @JsonView(View.Extended.class)
    @NotNull
    @Size(min = 1)
    private List<OrderItemDTO> orderItems;
    @JsonView(View.Basic.class)
    @NotNull
    private UserDTO user;
    @JsonView(View.Basic.class)
    @NotNull
    private DelivererDTO deliverer;
    @JsonView(View.Basic.class)
    @NotNull
    private RestaurantDTO restaurant;

    public interface OrderValidation {
    }

    public interface OrderStatusValidation {
    }

    public static class View {
        public interface Basic {
        }

        public interface Extended extends Basic {
        }
    }

}
