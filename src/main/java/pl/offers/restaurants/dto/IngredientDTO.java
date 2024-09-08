package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Setter
@Getter
@Builder(setterPrefix = "with")
public class IngredientDTO {

    @NotNull
    @JsonView(View.Basic.class)
    private UUID uuid;
    @NotBlank
    @JsonView(View.Basic.class)
    private String name;
    @NotNull
    @JsonView(View.Basic.class)
    private Boolean isAllergen;

    public static class View {
        public interface Basic {
        }
    }

}
