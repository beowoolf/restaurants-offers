package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.offers.restaurants.model.enums.Archive;

import javax.annotation.Nullable;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder(setterPrefix = "with")
public class UserDTO {

    @NotNull
    @JsonView(View.Id.class)
    private UUID uuid;
    @NotNull
    @Embedded
    @JsonView(View.Basic.class)
    private PersonalDataDTO personalData;
    @Nullable
    @JsonView(View.Extended.class)
    private List<DeliveryAddressDTO> deliveryAddress;
    @NotNull
    @Embedded
    @JsonView(View.Extended.class)
    private LogginDataDTO logginData;
    @Nullable
    @JsonIgnore
    @Null(groups = DataUpdateValidation.class)
    private List<OrderDTO> orders;
    @NotNull
    @JsonView(View.Extended.class)
    @Size(max = 0, groups = DataUpdateValidation.class)
    @Size(min = 1, max = 1, groups = NewOperationValidation.class)
    private List<OperationEvidenceDTO> operationEvidence;
    @Nullable
    @JsonView(View.Extended.class)
    private List<DiscountCodeDTO> discountCodes;
    @NotNull
    @JsonView(View.Extended.class)
    private Archive archive;

    public interface DataUpdateValidation {
    }

    public interface NewOperationValidation {
    }

    public static class View {
        public interface Id {
        }

        public interface Basic extends Id {
        }

        public interface Extended extends Basic {
        }
    }

}
