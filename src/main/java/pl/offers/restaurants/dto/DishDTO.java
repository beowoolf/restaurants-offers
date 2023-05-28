package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder(setterPrefix = "with")
public class DishDTO {

    @JsonView(View.Id.class)
    @NotNull
    private UUID uuid;
    @JsonView(View.Extended.class)
    @NotNull
    @Min(1)
    private Integer quantity;
    @JsonView(View.Extended.class)
    @NotNull
    private ProductDTO product;
    @JsonView(View.Extended.class)
    @Nullable
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
