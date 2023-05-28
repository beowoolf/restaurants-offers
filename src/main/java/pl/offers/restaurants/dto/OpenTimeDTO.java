package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.offers.restaurants.model.enums.DayOfWeek;

import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Setter
@Getter
@Builder(setterPrefix = "with")
public class OpenTimeDTO {

    @JsonView(View.Basic.class)
    @NotNull
    private UUID uuid;
    @JsonView(View.Extended.class)
    @NotNull
    private DayOfWeek dayOfWeek;
    @JsonView(View.Extended.class)
    @NotNull
    @Embedded
    private PeriodTimeDTO periodTime;
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
