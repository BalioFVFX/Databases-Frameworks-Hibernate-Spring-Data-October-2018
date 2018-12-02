package app.ccb.domain.dtos.importjson;


import javax.validation.constraints.NotNull;

public class BranchJsonDto {
    @NotNull
    private String name;

    public BranchJsonDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
