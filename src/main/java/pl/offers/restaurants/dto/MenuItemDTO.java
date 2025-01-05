package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.offers.restaurants.model.enums.VatTax;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder(setterPrefix = "with")
public class MenuItemDTO {

    @NotNull
    @JsonView(View.Basic.class)
    private UUID uuid;
    @NotBlank
    @JsonView(View.Basic.class)
    private String name;
    @Min(0)
    @NotNull
    @JsonView(View.Extended.class)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal nettoPrice;
    @NotNull
    @JsonView(View.Extended.class)
    private VatTax vatTax;
    @Min(0)
    @NotNull
    @JsonView(View.Extended.class)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal bruttoPrice;
    @NotNull
    @Size(min = 1)
    @JsonView(View.Extended.class)
    private List<DishDTO> dishes;
    @NotNull
    @JsonView(View.Extended.class)
    private RestaurantDTO restaurant;

    public static class View {
        public interface Basic {
        }

        public interface Extended extends Basic {
        }
    }

}
