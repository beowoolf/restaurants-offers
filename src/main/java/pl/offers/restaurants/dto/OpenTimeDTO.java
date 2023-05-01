package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import pl.offers.restaurants.model.enums.DayOfWeek;

import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;
import java.util.UUID;

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
    private PeriodTimeDTO periodTimeDTO;
    @JsonView(View.Extended.class)
    @NotNull
    private RestaurantDTO restaurantDTO;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public PeriodTimeDTO getPeriodTimeDTO() {
        return periodTimeDTO;
    }

    public void setPeriodTime(PeriodTimeDTO periodTimeDTO) {
        this.periodTimeDTO = periodTimeDTO;
    }

    public RestaurantDTO getRestaurantDTO() {
        return restaurantDTO;
    }

    public void setRestaurant(RestaurantDTO restaurantDTO) {
        this.restaurantDTO = restaurantDTO;
    }

    public static class View {
        public interface Basic {
        }

        public interface Extended extends Basic {
        }
    }

}
