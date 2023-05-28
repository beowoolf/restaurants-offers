package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Setter
@Getter
@Builder(setterPrefix = "with")
public class DeliveryAddressDTO {

    @JsonView(View.Basic.class)
    @NotNull
    private UUID uuid;
    @JsonView(View.Basic.class)
    @Nullable
    private String description;
    @JsonView(View.Extended.class)
    @NotNull
    private String street;
    @JsonView(View.Extended.class)
    @NotNull
    private String streetNumber;
    @JsonView(View.Extended.class)
    @NotNull
    private String localNumber;
    @JsonView(View.Extended.class)
    @NotNull
    private String postcode;
    @JsonView(View.Extended.class)
    @NotNull
    private String city;
    @JsonView(View.Extended.class)
    @Nullable
    private String borough;
    @JsonView(View.Extended.class)
    @Nullable
    private String county;
    @JsonView(View.Extended.class)
    @Nullable
    private String state;
    @JsonView(View.Basic.class)
    @NotNull
    private UserDTO user;

    public static class View {
        public interface Basic {
        }

        public interface Extended extends Basic {
        }
    }

}
