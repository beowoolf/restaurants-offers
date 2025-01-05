package pl.offers.restaurants.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import pl.offers.restaurants.model.enums.EvidenceType;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class OperationEvidence {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Instant date;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EvidenceType type;

    @Min(0)
    @NotNull
    @Column(scale = 2, precision = 12)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal amount;

    @NotNull
    @ManyToOne
    private User user;

}
