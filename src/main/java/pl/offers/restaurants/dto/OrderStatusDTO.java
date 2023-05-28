package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Setter
@Getter
@Builder(setterPrefix = "with")
@Embeddable
public class OrderStatusDTO {

    @JsonView(View.Basic.class)
    @NotNull
    private Instant orderTime;
    @JsonView(View.Basic.class)
    @NotNull
    private Boolean isPaid;
    @JsonView(View.Basic.class)
    @NotNull(groups = GiveOutStatusValidation.class)
    @Nullable
    private Instant giveOutTime;
    @JsonView(View.Basic.class)
    @NotNull(groups = DeliveryValidation.class)
    @Nullable
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
