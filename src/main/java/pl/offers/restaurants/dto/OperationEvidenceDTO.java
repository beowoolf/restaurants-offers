package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.offers.restaurants.model.enums.EvidenceType;

import java.math.BigDecimal;
import java.time.Instant;

@Setter
@Getter
@Builder(setterPrefix = "with")
public class OperationEvidenceDTO {

    @NotNull
    @JsonView(View.Basic.class)
    private Instant date;
    @NotNull
    @JsonView(View.Basic.class)
    private EvidenceType type;
    @Min(0)
    @NotNull
    @JsonView(View.Extended.class)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal amount;
    @NotNull
    @JsonIgnore
    private UserDTO user;

    public static class View {
        public interface Basic {
        }

        public interface Extended extends Basic {
        }
    }

}
