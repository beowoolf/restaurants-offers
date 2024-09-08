package pl.offers.restaurants.model;

import lombok.*;

import javax.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private UUID uuid;

    @NotBlank
    private String name;

    @NotNull
    @OneToMany
    private List<Ingredient> ingredients;

    @Nullable
    @OneToOne
    private Dish dish;

}
