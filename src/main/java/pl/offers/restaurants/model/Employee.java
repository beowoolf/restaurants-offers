package pl.offers.restaurants.model;

import lombok.*;
import pl.offers.restaurants.model.enums.Archive;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("employee")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Builder(setterPrefix = "with", builderMethodName = "baseBuilder")
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private UUID uuid;

    @NotNull
    @Embedded
    private PersonalData personalData;

    @NotNull
    @Embedded
    private LogginData logginData;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Archive archive;

}
