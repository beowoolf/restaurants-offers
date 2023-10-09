package pl.offers.restaurants.model;

import lombok.*;
import pl.offers.restaurants.model.enums.DiscountUnit;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class DiscountCode {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private UUID uuid;

    @NotBlank
    private String code;

    @Min(0)
    @NotNull
    @Column(scale = 2, precision = 12)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal discount;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DiscountUnit discountUnit;

    @NotNull
    @Embedded
    private Period period;

    @Nullable
    @ManyToMany
    private List<User> users;

    @Nullable
    @ManyToMany
    private List<Restaurant> restaurants;

}
