package pl.offers.restaurants.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class CompanyData {

    @NotNull
    @Column(name = "companyName")
    private String name;

    @NotNull
    @Embedded
    private Address address;

    @NotNull
    private String NIP;

    @NotNull
    private String REGON;

    @NotNull
    private String phone;

    @NotNull
    private String email;

}
