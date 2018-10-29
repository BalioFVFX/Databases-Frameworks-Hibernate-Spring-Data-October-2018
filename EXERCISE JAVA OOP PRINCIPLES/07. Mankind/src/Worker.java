public class Worker extends Human {

    private Double salary;
    private Double workingHours;
    private Double weekSalary;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Double getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Double workingHours) {
        this.workingHours = workingHours;
    }

    public Double getWeekSalary() {
        return weekSalary;
    }

    public void setWeekSalary(Double weekSalary) {
        this.weekSalary = weekSalary;
    }

    public Worker(String firstName, String lastName, double weekSalary, double workingHours){
        super(firstName, lastName);
        if(lastName.length() < 3){
            throw new IllegalArgumentException("Expected length more than 3 symbols!Argument: lastName");
        }
        if(weekSalary <= 10){
            throw new IllegalArgumentException("Expected value mismatch!Argument: weekSalary");
        }
        if(workingHours < 1 || workingHours > 12){
            throw new IllegalArgumentException("Expected value mismatch!Argument: workHoursPerDay");
        }

        this.weekSalary = weekSalary;
        this.workingHours = workingHours;
        this.salary = (this.weekSalary / this.workingHours) / 7;
    }
}
