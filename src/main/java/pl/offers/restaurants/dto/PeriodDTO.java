package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.offers.restaurants.validator.PeriodConstraint;

import javax.annotation.Nullable;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder(setterPrefix = "with")
@PeriodConstraint
@Embeddable
public class PeriodDTO {

    @JsonView(View.Basic.class)
    @Nullable
    private LocalDateTime begin;
    @JsonView(View.Basic.class)
    @Nullable
    private LocalDateTime end;

    public static class View {
        public interface Basic {
        }
    }

}
