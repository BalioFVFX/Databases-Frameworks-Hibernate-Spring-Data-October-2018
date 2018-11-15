package app.entities;

import app.entities.EmailConstrValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordConstraint {
    String message() default "INVALID PASSWORD";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int minLength();
    int maxLength();
    boolean containsDigit();
    boolean containsLowercase();
    boolean containsUppercase();
    boolean containsSpecialSymbol();
}