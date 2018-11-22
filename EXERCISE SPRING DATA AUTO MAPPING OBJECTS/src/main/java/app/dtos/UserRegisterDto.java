package app.dtos;

public class UserRegisterDto {
    private String email;
    private String password;
    private String repeatPassword;
    private String fullName;

    public UserRegisterDto(String email, String password, String repeatPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
