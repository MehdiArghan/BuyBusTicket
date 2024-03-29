package util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.Set;

public class CheckValidation {
    public static <T> boolean isValid(T object) {
        try {
            ValidatorFactory validatorFactory = Validation.byDefaultProvider().configure()
                    .messageInterpolator(new ParameterMessageInterpolator()).buildValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<T>> validate = validator.validate(object);
            if (!validate.isEmpty()) {
                for (ConstraintViolation<T> tConstraintViolation : validate) {
                    System.out.println(tConstraintViolation.getMessage());
                }
                validatorFactory.close();
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
