package pl.offers.restaurants.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import pl.offers.restaurants.model.enums.Sex;

import javax.annotation.Nullable;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
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
