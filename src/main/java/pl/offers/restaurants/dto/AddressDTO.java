package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.annotation.Nullable;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class AddressDTO {

    @NotNull
    @JsonView(View.Basic.class)
    private String street;
    @NotNull
    @JsonView(View.Basic.class)
    private String streetNumber;
    @NotNull
    @JsonView(View.Basic.class)
    private String localNumber;
    @NotNull
    @JsonView(View.Basic.class)
    private String postcode;
    @NotNull
    @JsonView(View.Basic.class)
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

    public static class View {
        public interface Basic {
        }

        public interface Extended extends Basic {
        }
    }

}
