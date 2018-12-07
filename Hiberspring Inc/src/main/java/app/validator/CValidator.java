package app.validator;

import javax.validation.Validation;
import javax.validation.Validator;

public class CValidator {
    private static Validator validator;


    static{
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public static <T> boolean isValid(T entity){
        return validator.validate(entity).size() <= 0;
    }
}
