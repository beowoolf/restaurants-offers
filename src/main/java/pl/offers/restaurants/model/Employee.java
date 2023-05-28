package pl.offers.restaurants.model;

import lombok.*;
import pl.offers.restaurants.model.enums.Archive;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with", builderMethodName = "baseBuilder")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("employee")
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull
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
