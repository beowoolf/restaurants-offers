package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import pl.offers.restaurants.model.enums.Archive;

import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with", builderMethodName = "baseBuilder")
public class EmployeeDTO {

    @JsonView(View.Basic.class)
    @NotNull
    private UUID uuid;
    @JsonView(View.Basic.class)
    @NotNull
    @Embedded
    private PersonalDataDTO personalData;
    @JsonView(View.Extended.class)
    @NotNull
    @Embedded
    private LogginDataDTO logginData;
    @JsonView(View.Extended.class)
    @NotNull
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
