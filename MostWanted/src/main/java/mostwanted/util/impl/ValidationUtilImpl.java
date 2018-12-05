package mostwanted.util.impl;

import mostwanted.util.ValidationUtil;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidationUtilImpl implements ValidationUtil {

    private Validator validator;

    public ValidationUtilImpl() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <E> boolean isValid(E entity) {
        if(this.validator.validate(entity).size() == 0){
            return true;
        }
        else{
            return false;
        }
    }
}
