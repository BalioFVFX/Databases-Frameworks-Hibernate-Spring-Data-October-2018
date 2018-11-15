package app.entities;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailConstrValidator implements ConstraintValidator<EmailConstraint, String> {



    public EmailConstrValidator() {

    }

    public void initialize(EmailConstraint constraint) {
    }

    public boolean isValid(String em, ConstraintValidatorContext context) {
        if(em == null){
            return false;
        }

        Pattern pattern = Pattern.compile("^[a-z0-9]+[a-z-_.0-9]*@[a-z]+[a-z-.]*[.]+[a-z]+$");
        return pattern.matcher(em).matches();
    }

}