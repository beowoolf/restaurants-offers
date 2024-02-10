package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Setter
@Getter
@Builder(setterPrefix = "with")
public class OrderItemDTO {

    @NotNull
    @JsonView(View.Basic.class)
    private UUID uuid;
    @Min(1)
    @NotNull
    @JsonView(View.Extended.class)
    private Integer quantity;
    @NotNull
    @JsonView(View.Extended.class)
    private MenuItemDTO menuItem;

    public static class View {
        public interface Basic {
        }

        public interface Extended extends Basic {
        }
    }

}
