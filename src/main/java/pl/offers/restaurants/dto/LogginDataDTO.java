package pl.offers.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@Builder(setterPrefix = "with")
@Embeddable
public class LogginDataDTO {

    @JsonView(View.Basic.class)
    @Size(min = 3)
    private String login;
    @JsonIgnore
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$")
    private String password;

    public static class View {
        public interface Basic {
        }
    }

}
