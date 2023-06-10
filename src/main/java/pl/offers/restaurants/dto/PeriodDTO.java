package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.offers.restaurants.validator.PeriodConstraint;

import javax.annotation.Nullable;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Setter
@Getter
@Embeddable
@PeriodConstraint
@NoArgsConstructor
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
