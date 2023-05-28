package pl.offers.restaurants.model;

import lombok.*;
import pl.offers.restaurants.model.enums.Archive;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull
    private UUID uuid;

    @NotNull
    @Embedded
    private PersonalData personalData;

    @Nullable
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeliveryAddress> deliveryAddress;

    @NotNull
    @Embedded
    private LogginData logginData;

    @Nullable
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @NotNull
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OperationEvidence> operationEvidence;

    @Nullable
    @ManyToMany(mappedBy = "users")
    private List<DiscountCode> discountCodes;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Archive archive;

}
