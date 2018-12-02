package app.ccb.domain.dtos.importjson;

import com.google.gson.annotations.SerializedName;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class EmployeeJsonDto {
    @SerializedName("full_name")
    private String fullName;
    private BigDecimal salary;
    @SerializedName("started_on")
    private String startedOn;
    @SerializedName("branch_name")
    private String branchName;

    public EmployeeJsonDto() {
    }

    @NotNull
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(String startedOn) {
        this.startedOn = startedOn;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
