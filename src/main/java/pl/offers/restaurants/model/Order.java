package pl.offers.restaurants.model;

import lombok.*;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull
    private UUID uuid;

    @Column(scale = 2, precision = 12)
    @Digits(integer = 10, fraction = 2)
    @Min(0)
    @NotNull
    private BigDecimal nettoPrice;

    @Column(scale = 2, precision = 12)
    @Digits(integer = 10, fraction = 2)
    @Min(0)
    @NotNull
    private BigDecimal bruttoPrice;

    @Nullable
    @ManyToOne
    private DiscountCode discountCode;

    @Column(scale = 2, precision = 12)
    @Digits(integer = 10, fraction = 2)
    @Min(0)
    @NotNull
    private BigDecimal amountToPayBrutto;

    @Nullable
    @Lob
    private String note;

    @NotNull
    @Embedded
    private OrderStatus status;

    @NotNull
    @OneToOne
    private DeliveryAddress deliveryAddress;

    @NotNull
    @Size(min = 1)
    @OneToMany
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
