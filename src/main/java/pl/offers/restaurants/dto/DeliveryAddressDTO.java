package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import java.util.UUID;

@Setter
@Getter
@Builder(setterPrefix = "with")
public class DeliveryAddressDTO {

    @NotNull
    @JsonView(View.Basic.class)
    private UUID uuid;
    @Nullable
    @JsonView(View.Basic.class)
    private String description;
    @NotNull
    @JsonView(View.Extended.class)
    private String street;
    @NotNull
    @JsonView(View.Extended.class)
    private String streetNumber;
    @NotNull
    @JsonView(View.Extended.class)
    private String localNumber;
    @NotNull
    @JsonView(View.Extended.class)
    private String postcode;
    @NotNull
    @JsonView(View.Extended.class)
    private String city;
    @Nullable
    @JsonView(View.Extended.class)
    private String borough;
    @Nullable
    @JsonView(View.Extended.class)
    private String county;
    @Nullable
    @JsonView(View.Extended.class)
    private String state;
    @NotNull
    @JsonView(View.Basic.class)
    private UserDTO user;

    public static class View {
        public interface Basic {
        }

        public interface Extended extends Basic {
        }
    }

}
