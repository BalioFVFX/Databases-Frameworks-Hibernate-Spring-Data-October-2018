package app.entities;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

    private int minLength;
    private int maxLength;
    private boolean containsDigit;
    private boolean containsLowercase;
    private boolean containsUppercase;
    private boolean containsSymbol;

    public PasswordValidator() {

    }

    public void initialize(PasswordConstraint constraint) {
        this.minLength = constraint.minLength();
        this.maxLength = constraint.maxLength();
        this.containsDigit = constraint.containsDigit();
        this.containsLowercase = constraint.containsLowercase();
        this.containsUppercase = constraint.containsUppercase();
        this.containsSymbol = constraint.containsSpecialSymbol();
    }

    public boolean isValid(String pw, ConstraintValidatorContext context) {
       if(pw == null){
           return false;
       }

       if(pw.length() < this.minLength){
           return false;
       }

       if(pw.length() > this.maxLength){
           return false;
       }

        if(this.containsDigit){
            Pattern pattern = Pattern.compile(".*\\d+.*");

            if(pattern.matcher(pw).matches() == false){
                System.out.println("NE E TAKA!");
                return false;
            }
        }

        if(this.containsLowercase == true){
            if(pw.equals(pw.toUpperCase())){
                return false;
            }
        }

        if(this.containsUppercase == true){
            if(pw.equals(pw.toLowerCase())){
                return false;
            }
        }

        if(this.containsSymbol == true) {
            Pattern pattern = Pattern.compile("[@#$%^&*()_+<>?]+");
            if(pattern.matcher(pw).find() == false){
                return false;
            }
        }
       return true;
    }

}