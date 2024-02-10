package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.offers.restaurants.model.enums.DayOfWeek;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Setter
@Getter
@Builder(setterPrefix = "with")
public class OpenTimeDTO {

    @NotNull
    @JsonView(View.Basic.class)
    private UUID uuid;
    @NotNull
    @JsonView(View.Extended.class)
    private DayOfWeek dayOfWeek;
    @NotNull
    @Embedded
    @JsonView(View.Extended.class)
    private PeriodTimeDTO periodTime;
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
