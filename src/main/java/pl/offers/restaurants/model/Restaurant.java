package pl.offers.restaurants.model;

import lombok.*;
import pl.offers.restaurants.model.enums.Archive;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Entity
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull
    private UUID uuid;

    @NotBlank
    private String name;

    @NotNull
    @Embedded
    private LogginData logginData;

    @NotNull
    @Embedded
    private CompanyData companyData;

    @NotNull
    @Size(max = 7)
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OpenTime> openTimes;

    @NotNull
    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders;

    @NotNull
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItem> menu;

    @NotNull
    @ManyToMany(mappedBy = "restaurants")
    private List<DiscountCode> discountCodes;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Archive archive;

}
