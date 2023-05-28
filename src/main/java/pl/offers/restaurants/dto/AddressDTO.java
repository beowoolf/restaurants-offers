package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder(setterPrefix = "with")
@Embeddable
public class AddressDTO {

    @JsonView(View.Basic.class)
    @NotNull
    private String street;
    @JsonView(View.Basic.class)
    @NotNull
    private String streetNumber;
    @JsonView(View.Basic.class)
    @NotNull
    private String localNumber;
    @JsonView(View.Basic.class)
    @NotNull
    private String postcode;
    @JsonView(View.Basic.class)
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

    public static class View {
        public interface Basic {
        }

        public interface Extended extends Basic {
        }
    }

}
