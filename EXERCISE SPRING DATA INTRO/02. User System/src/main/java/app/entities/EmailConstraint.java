package app.entities;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailConstrValidator.class)
public @interface EmailConstraint {
    String message() default "INVALID EMAIL";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}