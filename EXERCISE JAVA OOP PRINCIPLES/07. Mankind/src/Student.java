import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student extends Human{

    private String facultyNumber;

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public Student(String firstName, String lastName, String facultyNumber) {
        super(firstName, lastName);
        if(facultyNumber == null){
            throw new IllegalArgumentException("Invalid faculty number!");
        }
        String numPattern = "^([a-zA-Z0-9]{5,10})$";
        Pattern p = Pattern.compile(numPattern);
        Matcher m = p.matcher(facultyNumber);

        if (m.find()){
        }
        else{
            throw new IllegalArgumentException("Invalid faculty number!");
        }
        this.facultyNumber = facultyNumber;
    }
}
