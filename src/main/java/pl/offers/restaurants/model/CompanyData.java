package pl.offers.restaurants.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Embeddable
public class CompanyData {

    @NotNull
    @Column(name = "companyName")
    private String name;

    @Embedded
    @NotNull
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
