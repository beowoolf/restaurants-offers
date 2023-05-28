package pl.offers.restaurants.model;

import lombok.*;
import pl.offers.restaurants.validator.PeriodConstraint;

import javax.annotation.Nullable;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@PeriodConstraint
@Embeddable
public class Period {

    @Nullable
    private LocalDateTime begin;

    @Nullable
    private LocalDateTime end;

}
