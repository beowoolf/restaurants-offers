package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder(setterPrefix = "with")
public class DishDTO {

    @NotNull
    @JsonView(View.Id.class)
    private UUID uuid;
    @Min(1)
    @NotNull
    @JsonView(View.Extended.class)
    private Integer quantity;
    @NotNull
    @JsonView(View.Extended.class)
    private ProductDTO product;
    @Nullable
    @JsonView(View.Extended.class)
    @Null(groups = DataUpdateValidation.class)
    private List<MenuItemDTO> menuItems;

    public interface DataUpdateValidation {
    }

    public static class View {
        public interface Id {
        }

        public interface Basic extends Id {
        }

        public interface Extended extends Basic {
        }
    }

}
