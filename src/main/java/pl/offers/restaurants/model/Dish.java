package pl.offers.restaurants.model;

import lombok.*;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Dish {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private UUID uuid;

    @Min(1)
    @NotNull
    private Integer quantity;

    @NotNull
    @OneToOne
    private Product product;

    @Nullable
    @ManyToMany(mappedBy = "dishes")
    private List<MenuItem> menuItems;

}
