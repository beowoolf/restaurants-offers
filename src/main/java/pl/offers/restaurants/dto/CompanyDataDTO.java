package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder(setterPrefix = "with")
@Embeddable
public class CompanyDataDTO {

    @JsonView(View.Basic.class)
    @NotNull
    private String name;
    @JsonView(View.Extended.class)
    @Embedded
    @NotNull
    private AddressDTO address;
    @JsonView(View.Extended.class)
    @NotNull
    private String NIP;
    @JsonView(View.Extended.class)
    @NotNull
    private String REGON;
    @JsonView(View.Extended.class)
    @NotNull
    private String phone;
    @JsonView(View.Extended.class)
    @NotNull
    private String email;

    public static class View {
        public interface Basic {
        }

        public interface Extended extends Basic {
        }
    }

}
