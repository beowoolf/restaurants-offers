package pl.offers.restaurants.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.annotation.Nullable;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class DeliveryAddress {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
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
