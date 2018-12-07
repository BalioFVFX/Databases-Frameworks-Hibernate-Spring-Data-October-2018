package app.entity.dto.importjson;

import javax.validation.constraints.NotNull;

public class BranchJsonDto {
    private String name;
    private String town;

    public BranchJsonDto() {
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
