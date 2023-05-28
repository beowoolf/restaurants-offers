package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder(setterPrefix = "with")
public class ProductDTO {

    @JsonView(View.Basic.class)
    @NotNull
    private UUID uuid;
    @JsonView(View.Basic.class)
    @NotBlank
    private String name;
    @JsonView(View.Extended.class)
    @NotNull
    private List<IngredientDTO> ingredients;
    @JsonView(View.Extended.class)
    @Nullable
    private DishDTO dishDTO;
    @JsonView(View.Extended.class)
    private DishDTO dish;

    public static class View {
        public interface Basic {
        }

        public interface Extended extends Basic {
        }
    }

}
