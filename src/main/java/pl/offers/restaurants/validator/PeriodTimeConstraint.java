package pl.offers.restaurants.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
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
