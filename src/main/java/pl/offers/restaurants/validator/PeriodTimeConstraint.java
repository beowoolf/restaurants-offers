package pl.offers.restaurants.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PeriodTimeConstraintValidator.class)
public @interface PeriodTimeConstraint {

    String message() default "{pl.offers.restaurants.validator.PeriodTimeConstraint}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
