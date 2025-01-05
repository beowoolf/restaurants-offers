package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder(setterPrefix = "with")
public class OrderDTO {

    @NotNull
    @JsonView(View.Basic.class)
    private UUID uuid;
    @Min(0)
    @JsonView(View.Extended.class)
    @Digits(integer = 10, fraction = 2)
    @Null(groups = OrderValidation.class)
    private BigDecimal nettoPrice;
    @Min(0)
    @NotNull
    @JsonView(View.Extended.class)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal bruttoPrice;
    @Nullable
    @JsonView(View.Extended.class)
    private DiscountCodeDTO discountCode;
    @Min(0)
    @NotNull
    @JsonView(View.Extended.class)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal amountToPayBrutto;
    @Nullable
    @JsonView(View.Extended.class)
    private String note;
    @Embedded
    @JsonView(View.Basic.class)
    @Null(groups = OrderValidation.class)
    @NotNull(groups = OrderStatusValidation.class)
    private OrderStatusDTO orderStatus;
    @NotNull
    @JsonView(View.Extended.class)
    private DeliveryAddressDTO deliveryAddress;
    @NotNull
    @Size(min = 1)
    @JsonView(View.Extended.class)
    private List<OrderItemDTO> orderItems;
    @NotNull
    @JsonView(View.Basic.class)
    private UserDTO user;
    @NotNull
    @JsonView(View.Basic.class)
    private DelivererDTO deliverer;
    @NotNull
    @JsonView(View.Basic.class)
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
