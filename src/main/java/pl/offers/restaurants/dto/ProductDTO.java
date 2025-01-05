package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder(setterPrefix = "with")
public class ProductDTO {

    @NotNull
    @JsonView(View.Basic.class)
    private UUID uuid;
    @NotBlank
    @JsonView(View.Basic.class)
    private String name;
    @NotNull
    @JsonView(View.Extended.class)
    private List<IngredientDTO> ingredients;
    @Nullable
    @JsonView(View.Extended.class)
    private DishDTO dish;

    public static class View {
        public interface Basic {
        }

        public interface Extended extends Basic {
        }
    }

}
