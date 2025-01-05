package pl.offers.restaurants.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class OrderItem {

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
    private MenuItem menuItem;

}
