package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.annotation.Nullable;
import jakarta.validation.constraints.Null;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class DelivererDTO extends EmployeeDTO {

    @Nullable
    @JsonView(View.Extended.class)
    @Null(groups = NewDelivererValidation.class)
    private List<OrderDTO> orders;


    public interface NewDelivererValidation {
    }

    public static class View {
        public interface Id extends EmployeeDTO.View.Id {
        }

        public interface Basic extends EmployeeDTO.View.Basic {
        }

        public interface Extended extends Basic, EmployeeDTO.View.Extended {
        }
    }

}
