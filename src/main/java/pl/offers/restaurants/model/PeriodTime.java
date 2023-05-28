package pl.offers.restaurants.model;

import lombok.*;
import pl.offers.restaurants.validator.PeriodTimeConstraint;

import javax.annotation.Nullable;
import javax.persistence.Embeddable;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@PeriodTimeConstraint
@Embeddable
public class PeriodTime {

    @Nullable
    private LocalTime begin;

    @Nullable
    private LocalTime end;

}
