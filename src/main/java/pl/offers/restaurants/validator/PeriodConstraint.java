package pl.offers.restaurants.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PeriodConstraintValidator.class)
public @interface PeriodConstraint {

    String message() default "{pl.offers.restaurants.validator.PeriodConstraint}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
