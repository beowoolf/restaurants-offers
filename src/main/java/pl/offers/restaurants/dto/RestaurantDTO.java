package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.offers.restaurants.model.enums.Archive;

import javax.annotation.Nullable;
import javax.persistence.Embedded;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder(setterPrefix = "with")
public class RestaurantDTO {

    @NotNull
    @JsonView(View.Id.class)
    private UUID uuid;
    @NotBlank
    @JsonView(View.Basic.class)
    private String name;
    @NotNull
    @Embedded
    @JsonView(View.Basic.class)
    private LogginDataDTO logginData;
    @NotNull
    @Embedded
    @JsonView(View.Extended.class)
    private CompanyDataDTO companyData;
    @NotNull
    @Size(max = 7)
    @JsonView(View.Extended.class)
    private List<OpenTimeDTO> openTimes;
    @Nullable
    @JsonView(View.Extended.class)
    @Null(groups = DataUpdateValidation.class)
    private List<OrderDTO> orders;
    @Nullable
    @JsonView(View.Extended.class)
    @Null(groups = DataUpdateValidation.class)
    private List<MenuItemDTO> menuItems;
    @NotNull
    @JsonIgnore
    private List<DiscountCodeDTO> discountCodes;
    @NotNull
    @JsonView(View.Extended.class)
    private Archive archive;

    public interface DataUpdateValidation {
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
