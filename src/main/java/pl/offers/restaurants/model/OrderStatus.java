package pl.offers.restaurants.model;

import lombok.*;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
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
