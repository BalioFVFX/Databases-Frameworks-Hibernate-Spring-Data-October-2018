package app.domain.dto;

public class PersonCreateDto {
    private String firstName;
    private String lastName;
    private AdressCreateDto address;

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

    public AdressCreateDto getAddress() {
        return address;
    }

    public void setAddress(AdressCreateDto address) {
        this.address = address;
    }
}
