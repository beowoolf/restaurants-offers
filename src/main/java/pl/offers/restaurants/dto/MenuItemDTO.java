package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.offers.restaurants.model.enums.VatTax;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder(setterPrefix = "with")
public class MenuItemDTO {

    @JsonView(View.Basic.class)
    @NotNull
    private UUID uuid;
    @JsonView(View.Basic.class)
    @NotBlank
    private String name;
    @JsonView(View.Extended.class)
    @Digits(integer = 10, fraction = 2)
    @Min(0)
    @NotNull
    private BigDecimal nettoPrice;
    @JsonView(View.Extended.class)
    @NotNull
    private VatTax vatTax;
    @JsonView(View.Extended.class)
    @Digits(integer = 10, fraction = 2)
    @Min(0)
    @NotNull
    private BigDecimal bruttoPrice;
    @JsonView(View.Extended.class)
    @NotNull
    @Size(min = 1)
    private List<DishDTO> dishes;
    @JsonView(View.Extended.class)
    @NotNull
    private RestaurantDTO restaurant;

    public static class View {
        public interface Basic {
        }

        public interface Extended extends Basic {
        }
    }

}
