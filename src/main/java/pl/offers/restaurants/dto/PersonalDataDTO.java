package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.Joiner;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.offers.restaurants.model.enums.Sex;

import javax.annotation.Nullable;
import javax.persistence.Embeddable;

@Setter
@Getter
@Builder(setterPrefix = "with")
@Embeddable
public class PersonalDataDTO {

    @JsonView(View.Basic.class)
    @Nullable
    private String name;
    @JsonView(View.Basic.class)
    @Nullable
    private String surname;
    @JsonView(View.Extended.class)
    @Nullable
    private Sex sex;
    @JsonView(View.Extended.class)
    @Nullable
    private String phone;
    @JsonView(View.Extended.class)
    @Nullable
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
