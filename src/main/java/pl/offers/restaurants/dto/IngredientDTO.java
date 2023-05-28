package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Setter
@Getter
@Builder(setterPrefix = "with")
public class IngredientDTO {

    @JsonView(View.Basic.class)
    @NotNull
    private UUID uuid;
    @JsonView(View.Basic.class)
    @NotBlank
    private String name;
    @JsonView(View.Basic.class)
    @NotNull
    private Boolean isAllergen;

    public static class View {
        public interface Basic {
        }
    }

}
