package pl.offers.restaurants.model;

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
public class PeriodTime {

    @Nullable
    private LocalTime begin;

    @Nullable
    private LocalTime end;

}
