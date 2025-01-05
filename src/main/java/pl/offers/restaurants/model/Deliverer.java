package pl.offers.restaurants.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import javax.annotation.Nullable;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@DiscriminatorValue("deliverer")
public class Deliverer extends Employee {

    @Nullable
    @OneToMany(mappedBy = "deliverer")
    private List<Order> orders;

}
