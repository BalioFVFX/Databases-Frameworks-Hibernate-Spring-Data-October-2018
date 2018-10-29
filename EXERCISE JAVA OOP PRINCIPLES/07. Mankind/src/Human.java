public abstract class Human {
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Human(String firstName, String lastName){
        if(firstName == null){
            throw new IllegalArgumentException("Expected length at least 4 symbols!Argument: firstName");
        }
        if(lastName == null){
            throw new IllegalArgumentException("Expected length at least 4 symbols!Argument: firstName");
        }
        if(firstName.toUpperCase().charAt(0) != firstName.charAt(0)){
                throw new IllegalArgumentException("Expected upper case letter!Argument: firstName");
            }
            if(firstName.length() < 4){
                throw new IllegalArgumentException("Expected length at least 4 symbols!Argument: firstName");
            }
            if(lastName.length() < 3){
                throw new IllegalArgumentException("Expected length at least 3 symbols!Argument: lastName");
            }
            if(lastName.toUpperCase().charAt(0) != lastName.charAt(0)){
                throw new IllegalArgumentException("Expected upper case letter!Argument: lastName");
            }


        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Human(){

    }
}
