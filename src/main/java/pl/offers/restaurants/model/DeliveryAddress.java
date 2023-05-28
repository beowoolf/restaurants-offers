package pl.offers.restaurants.model;

import lombok.*;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Entity
public class DeliveryAddress {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull
    private UUID uuid;

    @Nullable
    private String description;

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

    @NotNull
    @ManyToOne
    private User user;

}
