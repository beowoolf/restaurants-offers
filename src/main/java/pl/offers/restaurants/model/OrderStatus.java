package pl.offers.restaurants.model;

import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class OrderStatus {

    @NotNull
    private Instant orderTime;

    @NotNull
    private Boolean isPaid;

    @NotNull
    private Instant giveOutTime;

    @NotNull
    private Instant deliveryTime;

}
