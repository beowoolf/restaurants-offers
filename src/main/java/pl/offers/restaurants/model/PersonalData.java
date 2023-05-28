package pl.offers.restaurants.model;

import lombok.*;
import pl.offers.restaurants.model.enums.Sex;

import javax.annotation.Nullable;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Embeddable
public class PersonalData {

    @Nullable
    private String name;

    @Nullable
    private String surname;

    @Nullable
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Nullable
    private String phone;

    @Nullable
    private String email;

}
