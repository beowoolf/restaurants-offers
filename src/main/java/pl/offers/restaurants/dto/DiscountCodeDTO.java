package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.offers.restaurants.model.enums.DiscountUnit;

import javax.annotation.Nullable;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder(setterPrefix = "with")
public class DiscountCodeDTO {

    @NotNull
    @JsonView(View.Basic.class)
    private UUID uuid;
    @NotBlank
    @JsonView(View.Basic.class)
    private String code;
    @Min(0)
    @NotNull
    @JsonView(View.Extended.class)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal discount;
    @NotNull
    @JsonView(View.Extended.class)
    private DiscountUnit discountUnit;
    @NotNull
    @Embedded
    @JsonView(View.Basic.class)
    private PeriodDTO period;
    @Nullable
    @JsonView(View.Extended.class)
    private List<UserDTO> users;
    @Nullable
    @JsonView(View.Extended.class)
    private List<RestaurantDTO> restaurants;

    public static class View {
        public interface Basic {
        }

        public interface Extended extends Basic {
        }
    }

}
