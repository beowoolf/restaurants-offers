package pl.offers.restaurants.model;

import lombok.*;
import pl.offers.restaurants.model.enums.VatTax;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menuitems")
@Builder(setterPrefix = "with")
public class MenuItem {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private UUID uuid;

    @NotBlank
    private String name;

    @Min(0)
    @NotNull
    @Column(scale = 2, precision = 12)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal nettoPrice;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VatTax vatTax;

    @Min(0)
    @NotNull
    @Column(scale = 2, precision = 12)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal bruttoPrice;

    @NotNull
    @ManyToMany
    @Size(min = 1)
    private List<Dish> dishes;

    @NotNull
    @ManyToOne
    private Restaurant restaurant;

}
