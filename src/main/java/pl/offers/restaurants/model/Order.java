package pl.offers.restaurants.model;

import lombok.*;

import javax.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Builder(setterPrefix = "with")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private UUID uuid;

    @Min(0)
    @NotNull
    @Column(scale = 2, precision = 12)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal nettoPrice;

    @Min(0)
    @NotNull
    @Column(scale = 2, precision = 12)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal bruttoPrice;

    @Nullable
    @ManyToOne
    private DiscountCode discountCode;

    @Min(0)
    @NotNull
    @Column(scale = 2, precision = 12)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal amountToPayBrutto;

    @Lob
    @Nullable
    private String note;

    @NotNull
    @Embedded
    private OrderStatus status;

    @NotNull
    @OneToOne
    private DeliveryAddress deliveryAddress;

    @NotNull
    @OneToMany
    @Size(min = 1)
    private List<OrderItem> orderItems;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @ManyToOne
    private Deliverer deliverer;

    @NotNull
    @ManyToOne
    private Restaurant restaurant;

}
