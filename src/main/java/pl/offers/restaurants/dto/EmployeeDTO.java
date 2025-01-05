package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import pl.offers.restaurants.model.enums.Archive;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with", builderMethodName = "baseBuilder")
public class EmployeeDTO {

    @NotNull
    @JsonView(View.Basic.class)
    private UUID uuid;
    @NotNull
    @Embedded
    @JsonView(View.Basic.class)
    private PersonalDataDTO personalData;
    @NotNull
    @Embedded
    @JsonView(View.Extended.class)
    private LogginDataDTO logginData;
    @NotNull
    @JsonView(View.Extended.class)
    private Archive archive;

    public static class View {
        public interface Id {
        }

        public interface Basic extends Id {
        }

        public interface Extended extends Basic {
        }
    }

}
