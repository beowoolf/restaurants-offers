package pl.offers.restaurants.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.offers.restaurants.model.PeriodTime;

public class PeriodTimeConstraintValidator implements ConstraintValidator<PeriodTimeConstraint, PeriodTime> {

    @Override
    public void initialize(PeriodTimeConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(PeriodTime periodTime, ConstraintValidatorContext context) {
        try {
            return periodTime.getBegin() == null || periodTime.getEnd() == null || periodTime.getBegin().isBefore(periodTime.getEnd());
        } catch (Exception e) {
            return false;
        }
    }

}
