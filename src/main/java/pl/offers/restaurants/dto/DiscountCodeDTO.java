package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.offers.restaurants.model.enums.DiscountUnit;

import javax.annotation.Nullable;
import javax.persistence.Embedded;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder(setterPrefix = "with")
public class DiscountCodeDTO {

    @JsonView(View.Basic.class)
    @NotNull
    private UUID uuid;
    @JsonView(View.Basic.class)
    @NotBlank
    private String code;
    @JsonView(View.Extended.class)
    @Digits(integer = 10, fraction = 2)
    @Min(0)
    @NotNull
    private BigDecimal discount;
    @JsonView(View.Extended.class)
    @NotNull
    private DiscountUnit discountUnit;
    @JsonView(View.Basic.class)
    @NotNull
    @Embedded
    private PeriodDTO period;
    @JsonView(View.Extended.class)
    @Nullable
    private List<UserDTO> users;
    @JsonView(View.Extended.class)
    @Nullable
    private List<RestaurantDTO> restaurantDTOS;
    @JsonView(View.Extended.class)
    private List<RestaurantDTO> restaurants;

    public static class View {
        public interface Basic {
        }

        public interface Extended extends Basic {
        }
    }

}
