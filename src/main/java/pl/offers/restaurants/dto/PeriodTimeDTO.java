package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.offers.restaurants.validator.PeriodTimeConstraint;

import javax.annotation.Nullable;
import javax.persistence.Embeddable;
import java.time.LocalTime;

@Setter
@Getter
@Builder(setterPrefix = "with")
@PeriodTimeConstraint
@Embeddable
public class PeriodTimeDTO {

    @JsonView(View.Basic.class)
    @Nullable
    private LocalTime begin;
    @JsonView(View.Basic.class)
    @Nullable
    private LocalTime end;

    public static class View {
        public interface Basic {
        }
    }

}
