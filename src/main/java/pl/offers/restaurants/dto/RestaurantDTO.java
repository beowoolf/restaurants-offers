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

    @JsonView(View.Id.class)
    @NotNull
    private UUID uuid;
    @JsonView(View.Basic.class)
    @NotBlank
    private String name;
    @JsonView(View.Basic.class)
    @NotNull
    @Embedded
    private LogginDataDTO logginData;
    @JsonView(View.Extended.class)
    @NotNull
    @Embedded
    private CompanyDataDTO companyData;
    @JsonView(View.Extended.class)
    @NotNull
    @Size(max = 7)
    private List<OpenTimeDTO> openTimes;
    @JsonView(View.Extended.class)
    @Nullable
    @Null(groups = DataUpdateValidation.class)
    private List<OrderDTO> orders;
    @JsonView(View.Extended.class)
    @Nullable
    @Null(groups = DataUpdateValidation.class)
    private List<MenuItemDTO> menuItems;
    @JsonIgnore
    @NotNull
    private List<DiscountCodeDTO> discountCodes;
    @JsonView(View.Extended.class)
    @NotNull
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
