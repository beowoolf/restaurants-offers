package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.annotation.Nullable;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class OrderStatusDTO {

    @NotNull
    @JsonView(View.Basic.class)
    private Instant orderTime;
    @NotNull
    @JsonView(View.Basic.class)
    private Boolean isPaid;
    @Nullable
    @JsonView(View.Basic.class)
    @NotNull(groups = GiveOutStatusValidation.class)
    private Instant giveOutTime;
    @Nullable
    @JsonView(View.Basic.class)
    @NotNull(groups = DeliveryValidation.class)
    private Instant deliveryTime;

    public interface GiveOutStatusValidation {
    }

    public interface DeliveryValidation {
    }

    public static class View {
        public interface Basic {
        }
    }

}
