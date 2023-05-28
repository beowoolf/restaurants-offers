package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Setter
@Getter
@Builder(setterPrefix = "with")
public class OrderItemDTO {

    @JsonView(View.Basic.class)
    @NotNull
    private UUID uuid;
    @JsonView(View.Extended.class)
    @NotNull
    @Min(1)
    private Integer quantity;
    @JsonView(View.Extended.class)
    @NotNull
    private MenuItemDTO menuItem;

    public static class View {
        public interface Basic {
        }

        public interface Extended extends Basic {
        }
    }

}
