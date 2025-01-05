package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
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
public class PeriodDTO {

    @Nullable
    @JsonView(View.Basic.class)
    private LocalDateTime begin;
    @Nullable
    @JsonView(View.Basic.class)
    private LocalDateTime end;

    public static class View {
        public interface Basic {
        }
    }

}
