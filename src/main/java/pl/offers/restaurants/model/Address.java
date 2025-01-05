package pl.offers.restaurants.model;


import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.annotation.Nullable;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Address {

    @NotNull
    private String street;

    @NotNull
    private String streetNumber;

    @NotNull
    private String localNumber;

    @NotNull
    private String postcode;

    @NotNull
    private String city;

    @Nullable
    private String borough;

    @Nullable
    private String county;

    @Nullable
    private String state;

}
