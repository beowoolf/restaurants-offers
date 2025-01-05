package pl.offers.restaurants.model;

import jakarta.persistence.Embeddable;
import lombok.*;
import pl.offers.restaurants.validator.PeriodConstraint;

import javax.annotation.Nullable;
import java.time.LocalDateTime;

@Setter
@Getter
@Embeddable
@PeriodConstraint
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Period {

    @Nullable
    private LocalDateTime begin;

    @Nullable
    private LocalDateTime end;

}
