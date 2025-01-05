package pl.offers.restaurants.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Ingredient {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private UUID uuid;

    @NotBlank
    private String name;

    @NotNull
    private Boolean isAllergen;

}
