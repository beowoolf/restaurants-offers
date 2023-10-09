package pl.offers.restaurants.model;

import lombok.*;
import pl.offers.restaurants.model.enums.DayOfWeek;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class OpenTime {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private UUID uuid;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @NotNull
    @Embedded
    private PeriodTime periodTime;

    @NotNull
    @ManyToOne
    private Restaurant restaurant;

}
