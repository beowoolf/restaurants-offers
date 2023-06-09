package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class CompanyDataDTO {

    @NotNull
    @JsonView(View.Basic.class)
    private String name;
    @NotNull
    @Embedded
    @JsonView(View.Extended.class)
    private AddressDTO address;
    @NotNull
    @JsonView(View.Extended.class)
    private String NIP;
    @NotNull
    @JsonView(View.Extended.class)
    private String REGON;
    @NotNull
    @JsonView(View.Extended.class)
    private String phone;
    @NotNull
    @JsonView(View.Extended.class)
    private String email;

    public static class View {
        public interface Basic {
        }

        public interface Extended extends Basic {
        }
    }

}
