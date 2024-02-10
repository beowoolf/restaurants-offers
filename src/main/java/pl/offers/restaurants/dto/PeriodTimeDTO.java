package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import pl.offers.restaurants.validator.PeriodTimeConstraint;

import javax.annotation.Nullable;
import jakarta.persistence.Embeddable;
import java.time.LocalTime;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@PeriodTimeConstraint
@Builder(setterPrefix = "with")
public class PeriodTimeDTO {

    @Nullable
    @JsonView(View.Basic.class)
    private LocalTime begin;
    @Nullable
    @JsonView(View.Basic.class)
    private LocalTime end;

    public static class View {
        public interface Basic {
        }
    }

}
