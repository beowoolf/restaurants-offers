package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import javax.validation.constraints.Null;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class DelivererDTO extends EmployeeDTO {

    @JsonView(View.Extended.class)
    @Nullable
    @Null(groups = NewDelivererValidation.class)
    private List<OrderDTO> orderDTOS;

    @Nullable
    public List<OrderDTO> getOrders() {
        return orderDTOS;
    }

    public void setOrders(@Nullable List<OrderDTO> orderDTOS) {
        this.orderDTOS = orderDTOS;
    }


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
