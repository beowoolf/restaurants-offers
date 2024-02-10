package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.Joiner;
import lombok.*;
import pl.offers.restaurants.model.enums.Sex;

import javax.annotation.Nullable;
import jakarta.persistence.Embeddable;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class PersonalDataDTO {

    @Nullable
    @JsonView(View.Basic.class)
    private String name;
    @Nullable
    @JsonView(View.Basic.class)
    private String surname;
    @Nullable
    @JsonView(View.Extended.class)
    private Sex sex;
    @Nullable
    @JsonView(View.Extended.class)
    private String phone;
    @Nullable
    @JsonView(View.Extended.class)
    private String email;

    @JsonView(View.Basic.class)
    public String nameAndSurname() {
        return Joiner.on(" ").skipNulls().join(name, surname);
    }

    public static class View {
        public interface Basic {
        }

        public interface Extended extends Basic {
        }
    }

}
