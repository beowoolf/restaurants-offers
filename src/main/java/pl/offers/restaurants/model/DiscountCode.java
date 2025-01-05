package pl.offers.restaurants.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import pl.offers.restaurants.model.enums.DiscountUnit;

import javax.annotation.Nullable;
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
