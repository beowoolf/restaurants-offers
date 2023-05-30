package pl.offers.restaurants.model;

import lombok.*;

import javax.annotation.Nullable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
